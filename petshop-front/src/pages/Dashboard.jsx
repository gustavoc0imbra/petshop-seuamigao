import { useEffect, useState } from "react";
import { getProducts } from "../services/productsService";
import BaseLayout from "../layouts/BaseLayout";
import { Box, Card, CardActionArea, CardActions, CardContent, CardHeader, CardMedia, Container, IconButton, Typography } from "@mui/material";
import { AddShoppingCart } from "@mui/icons-material";
import feed from "../assets/feed.jpg";
import catFeed from "../assets/cat_feed.jpg";
import { useNavigate } from "react-router";

export default function Dashboard() {
    const [products, setProducts] = useState([]);
    const navigator = useNavigate();

    const fetchProducts = async () => {
        const result = await getProducts();

        if (!result.success) {
            return;
        }

        setProducts(result.body.data);
    }

    const seeProductDetail = (id) => {
        navigator(`/product/${id}`);
    }

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <>
            <BaseLayout>
                <Container>
                    <Box
                        component="main"
                        sx={{
                            display: "flex",
                            flexWrap: "wrap",
                            justifyContent: "center",
                            alignItems: "center",
                            gap: 2,
                            p: 2
                        }}
                    >
                        {
                            products.map(product => {
                                return (
                                    <Card key={product.id}>
                                        <CardActionArea onClick={() => seeProductDetail(product.id)}>
                                            <CardMedia
                                                component="img"
                                                sx={{
                                                    width: "270px",
                                                    maxHeight: "250px"
                                                }}
                                                image={product.id % 2 === 0 ? feed : catFeed}
                                            />
                                            <CardHeader
                                                title={product.name}
                                                subheader={product.description}
                                                content=""
                                            />
                                            <CardContent>
                                                <Typography>
                                                    Peso: {new Intl.NumberFormat("pt-BR").format(product.weight)}
                                                </Typography>
                                                <Typography>
                                                    Preço: {new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(product.price)}
                                                </Typography>
                                            </CardContent>
                                        </CardActionArea>
                                        <CardActions
                                            sx={{
                                                display: "flex",
                                                justifyContent: "flex-end",
                                                alignItems: "center"
                                            }}
                                        >
                                            <IconButton>
                                                <AddShoppingCart />
                                            </IconButton>
                                        </CardActions>
                                    </Card>
                                )
                            })
                        }
                    </Box>
                </Container>

            </BaseLayout>
        </>
    );
}