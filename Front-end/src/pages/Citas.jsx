import { useParams } from "react-router-dom";

const Analytics = () => {
  const { aID } = useParams();
  return <h1> Citas / {aID} </h1>;
};
export default Analytics;
