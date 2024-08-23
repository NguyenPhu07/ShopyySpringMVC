let compareList = [];
const compareListContainer = document.getElementById('compareListContainer');
const compareListElement = document.getElementById('compareList');
const toggleButton = document.getElementById('toggleButton');
const toggleButtonText = toggleButton.querySelector('.text');

// Tải danh sách so sánh từ localStorage khi trang được tải lại
window.onload = function() {
    const savedCompareList = localStorage.getItem('compareList');
    if (savedCompareList) {
        compareList = JSON.parse(savedCompareList);
        updateCompareList();
        updateToggleButtonText();
    }
};

function saveCompareListToLocalStorage() {
    localStorage.setItem('compareList', JSON.stringify(compareList));
}

function addToCompare(productId, productName) {
    if (compareList.length >= 3) {
        alert("Bạn chỉ có thể so sánh tối đa 3 sản phẩm.");
        return;
    }

    if (!compareList.some(product => product.id === productId)) {
        compareList.push({ id: productId, name: productName });
        callThreeFuncImp(); // Lưu lại danh sách sau khi thêm
    } else {
        alert("Sản phẩm này đã có trong danh sách so sánh.");
    }
}

function updateCompareList() {
    compareListElement.innerHTML = '';
    compareList.forEach(product => {
        const itemDiv = document.createElement('div');
        itemDiv.className = 'compare-item';
        itemDiv.innerHTML = `
            <span>${product.name}</span>
            <button onclick="removeFromCompare('${product.id}')">X</button>
        `;
        compareListElement.appendChild(itemDiv);
    });
}

function removeFromCompare(productId) {
    compareList = compareList.filter(product => product.id !== productId); // list dùng filter lọc những thằngId khác thằng xóa ra là thằng là đc
      callThreeFuncImp();// Lưu lại danh sách sau khi xóa
}

function clearCompareList() {
    compareList = []; // đơn giản để rổng danh sách rỗng
      callThreeFuncImp(); // Lưu lại danh sách sau khi xóa
}

function callThreeFuncImp(){
    updateCompareList();
    updateToggleButtonText();
    saveCompareListToLocalStorage(); // Lưu lại danh sách sau khi thêm or xóa
}

  function submitComparison() {
        if (compareList.length === 0) {
            alert("Vui lòng chọn ít nhất một sản phẩm để so sánh.");
            return;
        }

        let form = document.createElement('form');
        form.method = 'GET';
        // gắn thô context để qua controller khác ko bị biến dạng URL 
        form.action = '/shopyy/products/compare-products';

        compareList.forEach(product => {
            let input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'productIds';
            input.value = product.id;
            form.appendChild(input);
        });

        document.body.appendChild(form);
        form.submit();
    }

function toggleCompareList() {
    if (compareListContainer.classList.contains('expanded')) {
        compareListContainer.classList.remove('expanded');
        compareListContainer.classList.add('collapsed');
        toggleButton.classList.add('collapsed');
    } else {
        compareListContainer.classList.remove('collapsed');
        compareListContainer.classList.add('expanded');
        toggleButton.classList.remove('collapsed');
    }
}

function updateToggleButtonText() {
    toggleButtonText.innerText = `So sánh (${compareList.length})`;
}
