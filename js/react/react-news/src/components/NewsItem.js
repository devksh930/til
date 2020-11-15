import React from "react";
import styled from "styled-components";

const NewsItemBlock = styled.div`
  display: flex;
  .thumbnail{
    margin-right: 1rem;
    img{
        display: block;
        width: 160px;
        height: 100px;
        object-fit: cover;
     }
  }
  .contents {
      h2{
          margin:0;
          a{
              color: black;
          }
      }
      p{
          margin:0;
          line-height: 1.5;
          margin-top: 0.5rem;
          white-space: normal;
      }
  }
  & + &{
      margin-top: 3rem;
  }
   
`;
const NewsItem = ({ article }) => {
  //title 제목, desc 내용, url url, urlToimag 이미
  const { title, description, url, urlToImage } = article;
  return (
    <NewsItemBlock>
      {urlToImage && (
        <div className="thumbnail">
          {/*img*/}
          <a href={url} target="_blank" rel="noopener noreferrer"></a>
          <img src={urlToImage} alt="thumbnail"/>
        </div>
      )}
      <div className="contents">
        {/*title*/}
        <h2>
          <a href={url} target="_blank" rel="noopener noreferrer">
            {title}
          </a>
        </h2>
        {/*내용 */}
        <p>{description}</p>
      </div>
    </NewsItemBlock>
  );
};

export default NewsItem;