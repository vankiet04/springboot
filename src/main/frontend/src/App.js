import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { Container, TextField, Button, Typography, Box } from "@mui/material";

function App() {
  return (
    <Container maxWidth="sm">
      <Box display="flex" flexDirection="column" alignItems="center" mt={5}>
        <img src={logo} className="App-logo" alt="logo" />
        <Typography variant="h4" component="h1" gutterBottom>
          Thêm Sản Phẩm đ
        </Typography>
        <form>
          <TextField
            label="Tên sản phẩm"
            variant="outlined"
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Giá sản phẩm"
            variant="outlined"
            fullWidth
            margin="normal"
            type="number"
            required
          />
          <TextField
            label="Mô tả sản phẩm"
            variant="outlined"
            fullWidth
            margin="normal"
            multiline
            rows={4}
            required
          />
          <Button variant="contained" color="primary" type="submit" fullWidth>
            Thêm sản phẩm
          </Button>
        </form>
      </Box>
    </Container>
  );
}

export default App;
