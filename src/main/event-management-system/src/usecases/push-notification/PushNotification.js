import React, {useEffect, useState} from 'react'
import {ADMIN, ROLE} from "../../common/Utils";
import * as Swal from "sweetalert2";


let eventSource = undefined;

let currentData = {id: "", eventTitle: "", idNumber: "", name: "", surname: ""};

export function startNotificationService() {
    if (localStorage.getItem(ROLE) === ADMIN) {
        eventSource = new EventSource("http://localhost:8080/notifications");
        eventSource.onmessage = (event) => {
            const newData = JSON.parse(event.data);
            if (newData.id !== currentData.id && localStorage.getItem(ROLE) === ADMIN) {

                if (currentData.id !== "") {
                    showNotification(generateText(newData));
                }
                currentData = newData;
            }


        }
    }
    return eventSource;
}

export function stopNotificationService() {
    eventSource.close();
}

function generateText(newData) {
    return newData.name + " " + newData.surname + " isimli kullanıcı " +
        newData.idNumber + " T.C Kimlik Numarası ile " + newData.eventTitle + " etkinliğine kaydoldu"
}


const Toast = Swal.mixin({
    toast: true,
    position: 'bottom-start',
    showConfirmButton: false,
    timer: 5000,
    onOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
})

export function showNotification(text) {
    Toast.fire({
        icon: 'info',
        title: "",
        text: text
    })
}


