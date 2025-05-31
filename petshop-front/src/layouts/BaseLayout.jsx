import {
  AccountCircle,
  Login,
  People,
  Pets,
  ShoppingCart,
  ShoppingCartCheckout,
} from "@mui/icons-material";
import {
  AppBar,
  Box,
  createTheme,
  Divider,
  Drawer,
  IconButton,
  List,
  ListItem,
  ListItemText,
  ThemeProvider,
  Toolbar,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { NavLink, useNavigate } from "react-router";
import "../index.css";
import { getCartItems } from "../services/cartService";
import { getAuthToken } from "../services/authService";

export default function BaseLayout({ children }) {
  const [cartOpen, setCartOpen] = useState(false);
  const [cartProducts, setCartProducts] = useState([]);
  const navigator = useNavigate();
  const theme = createTheme();

  const handleCartOpen = () => {
    const products = getCartItems();

    if (products) {
      setCartProducts(products);
    }

    setCartOpen(true);
  };

  const handleCartClose = () => {
    setCartOpen(false);
  };

  const handleProfileClick = () => {
    if (!getAuthToken()) {
      navigator("/login");
    }

    navigator("/profile");
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        <AppBar component="nav" position="static">
          <Toolbar>
            <Typography flex={1}>
              <NavLink to={"/"}>
                Petshop - Seu Amigão <Pets />
              </NavLink>
            </Typography>
            <IconButton onClick={handleProfileClick}>
              <AccountCircle />
            </IconButton>
            <IconButton onClick={handleCartOpen}>
              <ShoppingCart />
            </IconButton>
          </Toolbar>
        </AppBar>
        <Drawer
          anchor="right"
          variant="temporary"
          open={cartOpen}
          onClose={handleCartClose}
          ModalProps={{
            keepMounted: true,
          }}
        >
          <Toolbar />

          <Box
            sx={{
              p: 4,
            }}
          >
            <Typography>Meu Carrinho</Typography>
            <Divider />
            <List>
              {cartProducts.map((product) => {
                return (
                  <ListItem>
                    <ListItemText>{product.name}</ListItemText>
                    <ListItemText>
                      {new Intl.NumberFormat("pt-BR", {
                        style: "currency",
                        currency: "BRL",
                      }).format(product.price)}
                    </ListItemText>
                  </ListItem>
                );
              })}
            </List>
          </Box>
        </Drawer>
        {children}
      </ThemeProvider>
    </>
  );
}
