import axios from "axios";

const API_URL = "http://localhost:8080/api/citas";

export const listarCitas = async () => {
    return await axios.get(API_URL);
};

export const guardarCita = async (cita) => {
    return await axios.post(API_URL, cita);
};

export const eliminarCita = async (id) => {
    return await axios.delete(`${API_URL}/${id}`);
};