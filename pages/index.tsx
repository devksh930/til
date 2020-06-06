import React from 'react'
import Head from 'next/head'
import InsertionSort from '../components/insertionSort'
export default function Home() {
  return (
    <div>
    <Head>
      <title>Visualized Sort Algorithm</title>
    <link rel='icon' href='/favicon.ico'/>
      </Head>
      <div className='hero'>
      <h1 className='title'>Visualized Sort Algorithm</h1>
    
      <InsertionSort></InsertionSort>
      </div>

  

      <style jsx>{`
      .hero{
        width: 100%;
        color: #333;
      }
      .title{
        margin: 0;
        width: 100%;
        padding-top: 80px;
        line-height: 1.15;
        font-size: 48px;
      }
      .title,
      .description{
        text-align: center;
      }
      `}</style>
    </div>
  )
}
