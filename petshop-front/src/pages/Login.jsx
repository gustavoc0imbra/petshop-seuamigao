import { Box, Button, Tab, Tabs, TextField } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import { useState } from "react";
import { login } from "../services/authService";
import { useNavigate } from "react-router";
import { saveUser } from "../services/userService";

function TabPanel({ children, currentTab, index }) {
    return (
        <div
            role="tabpanel"
            hidden={currentTab !== index}
        >
            {children}
        </div>
    );
}

function a11yProps(index) {
    return {
        id: `tab-${index}`,
        'aria-controls': `tabpanel-${index}`,
    };
}

export default function Login() {
    const [tab, setTab] = useState(0);
    const [isSigninUp, setIsSiginUp] = useState(false);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigator = useNavigate();

    const changeTab = (event, newVl) => {
        setTab(newVl);
        setIsSiginUp(!isSigninUp);
    }

    const validateFields = () => {
        return email.trim().length !== 0 || password.trim().length !== 0;
    }

    const handleSigninClick = async (event) => {
        event.preventDefault();

        if (!validateFields()) {
            return;
        }

        let result;

        if (!isSigninUp) {
            result = await login({ email: email, password: password });
        }else {
            result = await saveUser({ name: name, email: email, password: password });
        }

        if (!result.success) {
            console.log(result.body.message);
            return;
        }

        console.log(result);


        localStorage.setItem('token', result.body.data.token);
        localStorage.setItem('user', JSON.stringify({
            id: result.body.data.user.id,
            name: result.body.data.user.name,
            email: result.body.data.user.email
        }));

        navigator("/");
    }

    return (
        <>
            <BaseLayout>
                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "center",
                        alignContent: "center"
                    }}
                >
                    <Box>
                        <Tabs value={tab} onChange={changeTab}>
                            <Tab label="Login" {...a11yProps(0)} />
                            <Tab label="Registrar-se" {...a11yProps(1)} />
                        </Tabs>
                        <Box
                            component="form"
                            sx={{
                                width: "500px",
                                display: "flex",
                                flexDirection: "column",
                                gap: 2,
                                p: 2
                            }}
                        >
                            <TextField sx={{ display: isSigninUp ? "flex" : "none" }} label="Nome" type="text" placeholder="Informe seu nome" onChange={(e) => setName(e.target.value)}  />
                            <TextField required label="E-mail" type="email" placeholder="Informe seu e-mail" onChange={(e) => setEmail(e.target.value)} />
                            <TextField required label="Senha" type="password" placeholder="Informe sua senha" onChange={(e) => setPassword(e.target.value)} />
                            <Box sx={{ display: "flex", justifyContent: "flex-end", gap: 1 }}>
                                <Button color="error" variant="contained">Cancelar</Button>
                                <Button variant="contained" onClick={handleSigninClick}>{ isSigninUp ? "Registrar" : "Entrar" }</Button>
                            </Box>
                        </Box>
                    </Box>

                </Box>
            </BaseLayout>
        </>
    );
}