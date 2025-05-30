export function getCartItems() {
    const items = localStorage.getItem('cart_itens');
    
    return items;
}

export function addCartItem(item) {

    let items = getCartItems();

    console.log(items)

    if (items === undefined) {
        items = [];
        items.push(item);
    }else {
        console.log("aqui")
        let alreadyInCart = items.findIndex((i) => i.id === item.id);

        if (alreadyInCart !== -1) {
            console.log("entrou aqui")
            item.amount = item.amount++;
        }

        items.push(item);
    }


    
    
    localStorage.setItem('cart_itens', JSON.stringify(items));
}

export function removeCartItem(itemId) {
    const items = getCartItems();

    let newCart = items.filter(item => item.id === itemId);

    localStorage.setItem('cart_itens', newCart);

    return newCart;
}