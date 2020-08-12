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

export function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
    let R = 6371; // Radius of the earth in km
    let dLat = deg2rad(lat2 - lat1);  // deg2rad below
    let dLon = deg2rad(lon2 - lon1);
    let a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2)
    ;
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    let d = R * c; // Distance in km
    return d;
}

function deg2rad(deg) {
    return deg * (Math.PI/180)
}

