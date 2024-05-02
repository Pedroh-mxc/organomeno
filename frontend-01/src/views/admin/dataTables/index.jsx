/*!
  _   _  ___  ____  ___ ________  _   _   _   _ ___   
 | | | |/ _ \|  _ \|_ _|__  / _ \| \ | | | | | |_ _| 
 | |_| | | | | |_) || |  / / | | |  \| | | | | || | 
 |  _  | |_| |  _ < | | / /| |_| | |\  | | |_| || |
 |_| |_|\___/|_| \_\___/____\___/|_| \_|  \___/|___|
                                                                                                                                                                                                                                                                                                                                       
=========================================================
* Horizon UI - v1.1.0
=========================================================

* Product Page: https://www.horizon-ui.com/
* Copyright 2023 Horizon UI (https://www.horizon-ui.com/)

* Designed and Coded by Simmmple

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/

// Chakra imports
import { Box, SimpleGrid, useColorModeValue } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import ColumnsTable from "views/admin/dataTables/components/ColumnsTable";
import FormVincularNota from "./components/FormVincularNota";

export default function Settings() {
  const [dataDespesas, setDataDespesa] = useState([])
  const [dataReceitas, setDataReceitas] = useState([])
  const [tipoDespesa, setTipoDespesa] = useState([])
  const [tipoReceita, setTipoReceita] = useState([])
  const [dataNotas, setDataNotas] = useState([])

  const valorNegativo = useColorModeValue("red.200", "red");
  const valorPositivo = useColorModeValue("green.200", "green")

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/despesas");
        const jsonData = await response.json();
        setTipoDespesa("Despesas")
        setDataDespesa(jsonData);
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/receitas");
        const jsonData = await response.json();
        setTipoReceita("Receitas")
        setDataReceitas(jsonData);
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/notas");
        const jsonData = await response.json();
        setDataNotas(jsonData)
      } catch (error) {
        console.error("Erro ao buscar dados:", error);
      }
    };
    fetchData();
  }, []);

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <SimpleGrid
        mb='20px'
        columns={{ sm: 1, md: 1 }}
        spacing={{ base: "20px", xl: "20px" }}>
        <ColumnsTable data={dataDespesas} tipo={tipoDespesa} corValor={valorNegativo} />
        <ColumnsTable data={dataReceitas} tipo={tipoReceita} corValor={valorPositivo} />
        <FormVincularNota despesas={dataDespesas} notasFiscais={dataNotas} />
      </SimpleGrid>
    </Box>
  );
}
