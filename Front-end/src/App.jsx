import { Route, Routes } from "react-router-dom";
import RootLayout from "./layouts/RootLayout";
import AllApps from "./pages/AllApps";
import Citas from "./pages/Citas";
import Pacientes from "./pages/Pacientes";

const App = () => {
  return (
    <RootLayout>
      <Routes>
        <Route path="/" element={<AllApps />} />
        <Route path="/citas" element={<Citas />} />
        <Route path="/pacientes" element={<Pacientes />} />
      </Routes>
    </RootLayout>
  );
};

export default App;
