import React, {Component} from "react";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
import CardContent from "@material-ui/core/CardContent";
import Card from "@material-ui/core/Card";
import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
import {showDialog, showDialogWithImage} from "../../common/Utils";
import ClearIcon from "@material-ui/icons/Clear";

class WelcomePage extends Component {
    backgroundImage = require('../../images/background10.jpg');

    style ={
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
    onCloseDialog = () =>{
        this.props.history.push("/events");
    }
    deneme = () =>{
        console.log("denemeeee");
        this.props.history.push("/events/Deneme");
    }
    render() {
      return(

              <div style={{
                  backgroundImage: "url(" + this.backgroundImage + ")",
                  backgroundRepeat: "no-repeat", backgroundAttachment: "fixed",
                  backgroundPosition: "center", backgroundSize: "cover",
                 height: "100.8vh"
              }}>
                  {showDialog("Etkinlikleri keşfedin", "Çevrenizdeki etkinlikleri keşfedin, kaydolun, katılın.", this.onCloseDialog)}
                 <Grid spacing={5} container alignItems={"center"} justify={"center"} style={{bottom: "0", position: "fixed", margin: "1vh"}}>

                    <Grid md={3} item> <Button color="secondary"
                             style={{fontStyle: "italic"}}
                             size={"large"}>
                         Etkinlik Sorumlusu Olarak Giriş Yap
                     </Button>
                    </Grid>
                     <Grid md={2} item>
                     <Button color="primary"
                             onClick={this.deneme}
                             style={{fontStyle: "italic"}}
                             size={"large"}>
                         Admin Olarak Giriş Yap
                     </Button>
                     </Grid>
                 </Grid>
              </div>
      )
    }
}
export default WelcomePage;
