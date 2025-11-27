import React, { useState, useEffect } from 'react';
import { listarCitas, guardarCita, eliminarCita } from '../services/CitaService';
import { Container, TextField, Button, Table, TableBody, TableCell, TableHead, TableRow, Paper, Typography, Box, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import Swal from 'sweetalert2';

export const CitasPage = () => {
    const [citas, setCitas] = useState([]);
    const [fecha, setFecha] = useState("");
    const [hora, setHora] = useState("");

    useEffect(() => { cargarDatos(); }, []);

    const cargarDatos = async () => {
        try {
            const resp = await listarCitas();
            setCitas(resp.data);
        } catch (error) {
            console.error("Error al conectar con Java", error);
        }
    };

    const handleGuardar = async (e) => {
        e.preventDefault();
        const nuevaCita = {
            fecha, 
            hora: `${hora}:00`,
            estado: "Pendiente",
            paciente: 1,
            psicologo: 1,
            servicio: 1,
        
        };
        try {
            await guardarCita(nuevaCita);
            Swal.fire("¡Éxito!", "Cita guardada", "success");
            cargarDatos();
        } catch (error) {
            Swal.fire("Error", "No se pudo guardar", "error");
        }
    };

    const handleEliminar = async (id) => {
        try {
            await eliminarCita(id);
            Swal.fire("Eliminado", "Cita borrada", "success");
            cargarDatos();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <Container maxWidth="md" sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom color="primary">Gestión de Citas</Typography>
            <Paper sx={{ p: 2, mb: 3 }}>
                <form onSubmit={handleGuardar}>
                    <Box sx={{ display: 'flex', gap: 2 }}>
                        <TextField label="Fecha" type="date" value={fecha} onChange={(e) => setFecha(e.target.value)} InputLabelProps={{ shrink: true }} required fullWidth />
                        <TextField label="Hora" type="time" value={hora} onChange={(e) => setHora(e.target.value)} InputLabelProps={{ shrink: true }} required fullWidth />
                        <Button variant="contained" type="submit">Agendar</Button>
                    </Box>
                </form>
            </Paper>
            <Paper>
                <Table>
                    <TableHead sx={{ bgcolor: '#1976d2' }}>
                        <TableRow>
                            <TableCell sx={{ color: 'white' }}>ID</TableCell>
                            <TableCell sx={{ color: 'white' }}>Fecha</TableCell>
                            <TableCell sx={{ color: 'white' }}>Hora</TableCell>
                            <TableCell sx={{ color: 'white' }}>Estado</TableCell>
                            <TableCell sx={{ color: 'white' }}>Acción</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {citas.map((cita) => (
                            <TableRow key={cita.idCita}>
                                <TableCell>{cita.idCita}</TableCell>
                                <TableCell>{cita.fecha}</TableCell>
                                <TableCell>{cita.hora}</TableCell>
                                <TableCell>{cita.estado}</TableCell>
                                <TableCell>
                                    <IconButton color="error" onClick={() => handleEliminar(cita.idCita)}><DeleteIcon /></IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Paper>
        </Container>
    );
};