import MapPicker from 'react-google-map-picker'
import React, {useState} from 'react'


const DefaultZoom = 15;

const MapView = (props) => {
    const DefaultLocation = {lat: props.data.lat, lng: props.data.lng};
    const [defaultLocation, setDefaultLocation] = useState(DefaultLocation);
    const [location, setLocation] = useState(defaultLocation);
    const [zoom, setZoom] = useState(DefaultZoom);

    function handleChangeLocation(lat, lng) {
        props.onLocationChange(lat, lng);
    }

    function handleChangeZoom(newZoom) {
        setZoom(newZoom);
    }

    function handleResetLocation() {
        setDefaultLocation({...DefaultLocation});
        setLocation({...DefaultLocation});
        setZoom(DefaultZoom);
    }

    return (
        <>
            <MapPicker defaultLocation={defaultLocation}
                       zoom={zoom}
                       onChangeLocation={handleChangeLocation}
                       onChangeZoom={handleChangeZoom}
                       apiKey='AIzaSyD07E1VvpsN_0FvsmKAj4nK9GnLq-9jtj8'/>
        </>
    );
}


export default MapView;
