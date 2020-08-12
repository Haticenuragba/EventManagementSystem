import Swal from 'sweetalert2/dist/sweetalert2.js'

import 'sweetalert2/src/sweetalert2.scss'

export function getDateOfToday() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;
    return today;
}

export const defaultImageUrl = 'https://res.cloudinary.com/diijhkryx/image/upload/v1596781090/no-image_q20lwb.png'

let isDark = false;

export function setIsDark(booleanExpression) {
    isDark = booleanExpression;
}

export function getIsDark() {
    return isDark;
}

export function showSuccessDialog(text) {
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: text,
        showConfirmButton: false,
        timer: 1500
    })
}

