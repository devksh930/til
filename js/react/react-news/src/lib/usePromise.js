import React, { useEffect, useState } from "react";

const usePromise = (promiseCreator, deps) => {

  const [loading, setLoading] = useState(false);
  const [resolved, setResolved] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const process = async () => {
      setLoading(true);
      try {

        const result = await promiseCreator();
        setResolved(result);
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };
    process();
  }, deps);
  return [loading, resolved, error];
};
export default usePromise;
