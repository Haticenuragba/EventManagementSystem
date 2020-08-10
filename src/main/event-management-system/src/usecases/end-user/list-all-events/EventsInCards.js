import MediaCard from "./MediaCard";
import React from 'react';

export default function EventsInCards({events}) {
    return(
        <section className="events">
            {
                events.map( anEvent => (
                    <MediaCard event={anEvent}/>
                ) )
            }
        </section>
    )

}
