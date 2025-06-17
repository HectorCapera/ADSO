import { Route, Routes } from "react-router-dom";
import RootLayout from "./layouts/RootLayout";
import AllApps from "./pages/AllApps";
import Citas from "./pages/Citas";
import DashboardInicio from "./pages/DashboardInicio";

const App = () => {
  return (
    <RootLayout>
      <Routes>
        <Route path="/" element={<AllApps />} />
        <Route path="/Citas" element={<Citas />} />
        <Route path="/DashboardInicio" element={<DashboardInicio />} />
      </Routes>
    </RootLayout>
  );
};

export default App;
