import React, {Component} from "react";
import Grid from "@material-ui/core/Grid";
import unauthorizedImage from '../images/Unauthorized403.png';

class Unauthorized extends Component {

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


    render() {
        return (

            <Grid alignContent={"center"} alignItems={"center"} justify={"center"} container>
              <Grid container p={5} style={{marginTop:"2vh", width: "90%", height: "90vh",
                  alignContent: "center", justifyContent: "center"}}>
                  <img src={unauthorizedImage} height={"100%"} />
              </Grid>
            </Grid>
        )
    }
}

export default Unauthorized;
