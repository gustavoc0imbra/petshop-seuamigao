import { Box, Typography } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import { useEffect, useState } from "react";
import { getAuthToken, getAuthUser, login } from "../services/authService";
import { useNavigate } from "react-router";
import { getUser, saveUser } from "../services/userService";
import { DataGrid } from "@mui/x-data-grid";
import { getOrders } from "../services/ordersService";

export default function Profile() {
    const [user, setUser] = useState({});
    const [orders, setOrders] = useState([]);
    const navigator = useNavigate();

    const fetchUser = async () => {
        const token = getAuthToken();
        const authUser = getAuthUser();

        if(!token || !authUser) {
            navigator("/login");
        }

        const result = await getUser(token, authUser.id);
        const ordersResult = await getOrders(token, authUser.id);

        if(!result.success || !ordersResult.success) {
            navigator("/login");
        }

        setUser(result.body.data);
        setOrders(ordersResult.body.data);
    };

    useEffect(() => {
        fetchUser();
    }, []);

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
                    <Typography>Olá { user.name }</Typography>
                    <Typography>Seus ultimos pedidos são:</Typography>
                    <DataGrid
                        columns={[
                            {
                                field: "id",
                                headerName: "Cód. Pedido"
                            },
                            {
                                field: "createdAt",
                                headerName: "Feito em"
                            },
                            {
                                field: "status",
                                headerName: "Status"
                            },
                        ]}
                        rows={orders}

                    />
                </Box>
            </BaseLayout>
        </>
    );
}