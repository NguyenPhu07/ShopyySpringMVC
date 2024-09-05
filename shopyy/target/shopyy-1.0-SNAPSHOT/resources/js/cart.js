/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function addToCart(productId) {
    fetch(`/shopyy/api/addCart/${productId}`)
            .then(res => res.json())
            .then(data => {
                var count = document.querySelector('.counter');
                if (count !== null) {
                    count.innerText = data
                }
            })
}

function inscProduct(productId) {
    fetch(`/shopyy/api/addProduct/${productId}`)
            .then(res => res.json())
            .then(data => {
                // Cập nhật số lượng sản phẩm cụ thể
                var quantity = document.querySelector('.quantity-' + productId);
                if (quantity !== null) {
                    quantity.innerText = data.productQuantity;
                }
                // cập nhật số lượng giỏ hàng
                var count = document.querySelector('.counter');
                if (count !== null) {
                    count.innerText = data.totalQuantity;
                }
            })
    calculateTotals()// tính lại tổng tiền        
}
function descProduct(productId) {
    fetch(`/shopyy/api/reduceProduct/${productId}`)
            .then(res => res.json())
            .then(data => {
                // kiểm tra lượng sản phẩm = 0 không, bằng thì cook
                if (data.productQuantity === 0) {
                    var prod = document.getElementById('product-' + productId);
                    if (prod !== null) {
                        prod.remove(); // Xóa sản phẩm khỏi cây DOM
                    }
                } else {
                    // Cập nhật số lượng sản phẩm cụ thể nếu vẫn còn trong giỏ
                    var quantity = document.querySelector('.quantity-' + productId);
                    if (quantity !== null) {
                        quantity.innerText = data.productQuantity;
                    }
                }

                // Cập nhật tổng số lượng sản phẩm trong giỏ hàng (navbar)
                var count = document.querySelector('.counter');
                if (count !== null) {
                    count.innerText = data.totalQuantity;
                }
            })
    calculateTotals()// tính lại tổng tiền
}
function removeProduct(productId) {
    fetch(`/shopyy/api/removeCart/${productId}`)
            .then(res => res.json())
            .then(data => {
                var prod = document.getElementById('product-' + productId);
                if (prod !== null) {
                    prod.remove(); // Xóa sản phẩm khỏi cây DOM
                }

                // Cập nhật tổng số lượng sản phẩm trong giỏ hàng (navbar)
                var count = document.querySelector('.counter');
                if (count !== null) {
                    count.innerText = data
                }
            })
    calculateTotals()// tính lại tổng tiền
}

function calculateTotals() {
    fetch(`/shopyy/api/cart`)
            .then(res => res.json())
            .then(data => {
                let grandTotal = 0; // Tổng tiền của tất cả các sản phẩm

                // Duyệt qua các sản phẩm trong giỏ hàng thông qua dât
                for (const productId in data) {
                    if (data.hasOwnProperty(productId)) {//hasOwnProperty kiểm tra xem có keyProId đó không
                        const product = data[productId];
                        const totalProductPrice = product.price * product.quantity; // Tính tổng tiền của từng sản phẩm

                        // Cập nhật tổng tiền cho sản phẩm đó trên giao diện
                        const totalProId = document.getElementById('total-' + productId);
                        if (totalProId !== null) {
                            totalProId.innerText = totalProductPrice.toLocaleString() + 'đ'; // Hiển thị giá với định dạng và đơn vị tiền
                        }

                        // Cộng vào tổng tiền của toàn bộ giỏ hàng
                        grandTotal += totalProductPrice;
                    }
                }

                // Cập nhật tổng tiền của giỏ hàng trên giao diện
                const grandTotalElement = document.getElementById('grand-total');
                if (grandTotalElement !== null) {
                    grandTotalElement.innerText = grandTotal.toLocaleString() + 'đ'; // Hiển thị tổng tiền với định dạng và đơn vị tiền
                }
            })
            .catch(error => console.error('Error:', error));
}


