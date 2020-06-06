import {range, shuffle} from 'lodash';
import {useState, FC, SetStateAction, Dispatch} from 'react';
import {Transform} from 'stream';

const SIZE = 30;
const getArr = () => shuffle(range(1, SIZE + 1))

const swap = (arr, a, b) => {
    const tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp
}

type TSetArr = Dispatch<SetStateAction<number[]>>
type TSetIdx = Dispatch<SetStateAction<number>>

const delaySetArr = (arr: number[], setArr: TSetArr) => {

    return new Promise((resolve) => {
        setArr(arr)
        setTimeout(() => resolve(), 50)
    })
}
const sort = async (arr: number[], setArr: TSetArr, setidxI: TSetIdx, setidxJ: TSetIdx) => {
    {
        /*  https://en.wikipedia.org/wiki/Insertion_sort
         i ← 1
      while i < length(A)
          j ← i
          while j > 0 and A[j-1] > A[j]
              swap A[j] and A[j-1]
              j ← j - 1
          end while
          i ← i + 1
      end while
        */
    }
    let i = 1;
    while (i < arr.length) {
        setidxJ(i)
        let j = i;
        while (j > 0 && arr[j - 1] > arr[j]) {

            swap(arr, j, j - 1)
            await delaySetArr(arr, setArr)
            j = j - 1;
            setidxJ(j - 1)
        }
        i = i + 1;
        setidxI(i)
    }
}

//callback hell -> promis -> async/await es7
interface IPropsBar {
    value: number
    idx: number
}

const Bar: FC<IPropsBar> = (props) => {
    const {value, idx} = props
    const style = {height: value * 10, transform: `translateX(${idx * 22}px)`}
    return (
        <>
            <div style={style} className='bar'/>

            <style jsx>{`
    .bar{
        position: absolute;
        width: 20px;
        background-color: black;
                }

                `}</style>
        </>
    )
}

export default () => {

    const [arr, setArr] = useState(getArr())
    const [idxI, setidxI] = useState(1)
    const [idxJ, setidxJ] = useState(1)
    const [isRunning, setIsRunning] = useState(false)

    const handleShuffle = () => {
        setArr(getArr())
    }
    const handleSort = () => {
        // console.log("before sort\n"+arr);
        const sortedArr = [...arr]
        sort(sortedArr, setArr, setidxI, setidxJ)
        setArr(sortedArr)
        // console.log("after sort\n"+arr);
        setIsRunning(true)
    }

    return (
        <div>
            <div className='board'>

                {arr.map((value, i) => <Bar key={i} value={value} idx={i}/>)}


            </div>
            <div className='index i' style={{transform: `translateX(${idxI * 22}px)`}}>i</div>
            <div className='index j' style={{transform: `translateX(${idxJ * 22}px)`}}>j</div>


            <div className='buttonBox'>
                {!isRunning && <button onClick={handleShuffle}>shuffle</button>                }
                {!isRunning && <button onClick={handleSort}>sort</button>}
                {isRunning && <div className='running'>Running sort.....</div>}
            </div>
            <style jsx>{`
            .board{
                width: 100%;
                height: 200px;
                background-color: green;
                color: white;
                transform: rotateX(180deg);
                }
            .buttonBox{ 
               width: 100%;
               height: 60px;
               background-color: gray;        
               text-align:right;
            }
            button{ 
                font-size: 40px;
            }
            .running{
            font-size: 40px;
            }
            .index{
            position: absolute;
            width: 20px;
            background-color: blue;
            opacity: 0.8;
            }
            .index.j{
            background-color: blue;
            color: white;

            }
            .index.i{
            background-color: yellow;
            color: black;

            }
          
        `}</style>
        </div>
    )
}