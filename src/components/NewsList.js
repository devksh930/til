import React, { useEffect, useState } from "react";
import NewsItem from "./NewsItem";
import styled from "styled-components";
import axios from "axios";

const uri = "https://newsapi.org/v2/top-headlines?country=kr";
const apikey = `&apiKey=197b4c27cb4c40d79476c43b8a3b7bee`;


const NewsListBlock = styled.div`
      box-sizing: border-box;
      padding-bottom: 3rem;
      width: 768px;
      margin: 0 auto;
      margin-top: 2rem;
      
      @media screen and (max-width: 768px){
          width: 100%;
          padding-left: 1rem;
          padding-right: 1rem;
      }
`;

const NewsList = ({ category }) => {
  const [articles, setArticles] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const query = category === "all" ? "" : `&category=${category}`;

        const response = await axios.get(uri + query + apikey);
        setArticles(response.data.articles);
      } catch (e) {
        console.error(e);
      }
      setLoading(false);
    };
    fetchData();
  }, [category]);//빈종속리스트를 주면 처음 렌더링 될때만 이함수를 정의

  if (loading) {
    return (
      <NewsListBlock>뉴스 목록 로딩중 ....</NewsListBlock>
    );
  }

  if (!articles) {
    return null;
  }


  return (
    <NewsListBlock>
      {
        articles.map(articles => (
          <NewsItem key={articles.url} article={articles}> </NewsItem>
        ))}
    </NewsListBlock>
  );
};

export default NewsList;