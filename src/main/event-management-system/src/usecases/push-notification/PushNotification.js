import React, { useEffect, useState } from 'react'
import {ADMIN, ROLE} from "../../common/Utils";


let eventSource = undefined;

export function startNotificationService() {
    if(localStorage.getItem(ROLE) === ADMIN) {
        eventSource = new EventSource("http://localhost:8080/notifications");
        eventSource.onmessage = (event) => {
            const usage = JSON.parse(event.data);
            console.log(usage);
        }
    }
    return eventSource;
}

export function stopNotificationService() {
    eventSource.close();
}


