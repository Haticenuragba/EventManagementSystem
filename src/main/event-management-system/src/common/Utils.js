import Swal from 'sweetalert2/dist/sweetalert2.js'

import 'sweetalert2/src/sweetalert2.scss'

export function getDateOfToday() {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;
    return today;
}

export function getDaysLaterInMilliseconds(n) {
    let today = new Date();
    return new Date(today.getTime() + n * 24 * 60 * 60 * 1000).getTime();
}

export function dateStringToObject(string) {
    let from = string.split("-");
    return new Date(from[0], from[1] - 1, from[2]);

}

export const defaultImageUrl = 'https://res.cloudinary.com/diijhkryx/image/upload/v1596781090/no-image_q20lwb.png'


export function setIsDark(booleanExpression) {
    localStorage.setItem("isDark", booleanExpression);
}

export function getIsDark() {
    if (localStorage.getItem("isDark") === null)
        setIsDark(false);

    return (localStorage.getItem("isDark") === "true");

}

export function showSuccessDialog(text) {
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: text,
        showConfirmButton: false,
        background: getIsDark() ? '#1f1f1f' : '#fff',
        timer: 1500
    })
}


export function showErrorDialog(text) {
    Swal.fire({
        position: 'top-end',
        icon: 'error',
        title: text,
        background: getIsDark() ? '#1f1f1f' : '#fff',
        showConfirmButton: false,
        timer: 1500
    })
}

export function showDialogWithImage(text, image) {
    Swal.fire({
        html: "<img src=\"data:image/jpg;base64, " + image + "\" style='width: 40vh; height: 40vh'/>",
        position: 'center',
        icon: 'success',
        title: text,
        width: '40%',
        background: getIsDark() ? '#1f1f1f' : '#fff',
        confirmButtonColor: '#303f9f',
        showConfirmButton: true,
        confirmButtonText: "Tamam"
    })
}


export function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
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
    return deg * (Math.PI / 180)
}

export const headers = {
    'Content-Type': 'application/json',
    'Authorization': localStorage.getItem("token")
}

export const ROLE = "role";
export const TOKEN = "token";
export const ADMIN = "ADMIN";
export const EVENT_MANAGER = "EVENT_MANAGER";

