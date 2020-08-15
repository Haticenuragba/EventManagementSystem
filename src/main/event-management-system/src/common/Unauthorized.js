import React, {Component} from "react";
import Grid from "@material-ui/core/Grid";

class Unauthorized extends Component {
    backgroundImage = require('../images/Unauthorized403.png');

    style = {
        root: {
            minWidth: 275,
        },
        bullet: {
            display: 'inline-block',
            margin: '0 2px',
            transform: 'scale(0.8)',
        },
        title: {
            fontSize: 14,
        },
        pos: {
            marginBottom: 12,
        },
    };

    constructor(props) {
        super(props);
    }

    navigateToHomePage = () => {
        this.props.history.push("/");
    }

    render() {
        return (

            <div style={{
                backgroundImage: "url(" + this.backgroundImage + ")",
                backgroundRepeat: "no-repeat", backgroundAttachment: "fixed",
                backgroundPosition: "center", backgroundSize: "cover",
                height: "100vh"
            }}>
                <Grid container alignItems={"center"} justify={"center"}>


                </Grid>
            </div>
        )
    }
}

export default Unauthorized;
