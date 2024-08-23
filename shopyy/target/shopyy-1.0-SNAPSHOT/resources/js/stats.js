/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    const timePeriodSelect = document.getElementById('time-period');
    const monthQuarterSelect = document.getElementById('month-quarter-select');
    const yearSelect = document.getElementById('year-select');

    // Thêm các tùy chọn năm từ 2020 đến 2024
    for (let year = 2020; year <= 2024; year++) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearSelect.appendChild(option);
    }

    // Khi chọn thời gian, cập nhật các tùy chọn tháng/quý
    timePeriodSelect.addEventListener('change', function () {
        const selectedPeriod = this.value;

        // Xóa các tùy chọn cũ
        monthQuarterSelect.innerHTML = '<option value="">Chọn tháng hoặc quý</option>';

        // Nếu người dùng chọn "Tháng"
        if (selectedPeriod === 'month') {
            for (let i = 1; i <= 12; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.textContent = `Tháng ${i}`;
                monthQuarterSelect.appendChild(option);
            }
        }
        // Nếu người dùng chọn "Quý"
        else if (selectedPeriod === 'quarter') {
            for (let i = 1; i <= 4; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.textContent = `Quý ${i}`;
                monthQuarterSelect.appendChild(option);
            }
        }
    });

});