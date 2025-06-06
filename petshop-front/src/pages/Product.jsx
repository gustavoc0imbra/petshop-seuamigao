import { useEffect, useState } from "react";
import { getProductById } from "../services/productsService";
import BaseLayout from "../layouts/BaseLayout";
import { Box, Button, Card, CardContent, CardMedia, Typography } from "@mui/material";
import { AddShoppingCart } from "@mui/icons-material";
import { useParams } from "react-router";
import feed from "../assets/feed.jpg";
import catFeed from "../assets/cat_feed.jpg";
import { addCartItem } from "../services/cartService";

export default function Product() {
    const [product, setProduct] = useState({});
    let params = useParams();

    const fetchProduct = async () => {
        const result = await getProductById(params.id);

        if (!result.success) {
            return;
        }

        setProduct(result.body.data);
    }

    const handleAddCartItem = () => {
        console.log(product);
        addCartItem(product);
    }

    useEffect(() => {
        fetchProduct();
    }, []);

    return (
        <>
            <BaseLayout>
                <Box sx={{
                    display: "flex",
                    justifyContent: "center",
                    alignContent: "center",
                    p: 2
                }}>
                    <Card
                        sx={(theme) => ({
                            display: "flex",
                            [theme.breakpoints.up('xs')]: {
                                flexDirection: "column"
                            },
                            [theme.breakpoints.up('sm')]: {
                                flexDirection: "row"
                            }
                        }
                        )}>
                        <CardMedia
                            component="img"
                            sx={{
                                maxHeight: "250px",
                                flex: 1
                            }}
                            image={product.id % 2 === 0 ? feed : catFeed}
                        />
                        <CardContent sx={{ flex: 1, display: "flex", flexDirection: "column", justifyContent: "space-around" }}>
                            <Box>
                                <Typography variant="h5">{product.name}</Typography>
                                <Typography variant="body2">{product.description}</Typography>
                            </Box>
                            <Box>
                                <Typography>
                                    Peso: {new Intl.NumberFormat("pt-BR").format(product.weight)}
                                </Typography>
                                <Typography>
                                    Preço: {new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(product.price)}
                                </Typography>
                            </Box>
                            
                            <Button
                                color="success"
                                variant="contained"
                                endIcon={<AddShoppingCart />}
                                onClick={handleAddCartItem}
                            >
                                Adicionar ao carrinho
                            </Button>
                        </CardContent>
                    </Card>
                </Box>

            </BaseLayout>
        </>
    );
}