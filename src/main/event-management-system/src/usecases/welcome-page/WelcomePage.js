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
import SendIcon from "@material-ui/icons/Send";

class WelcomePage extends Component {
    backgroundImage = require('../../images/background10.jpg');

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

    navigateToEventGrid = () => {
        this.props.history.push("/events");
    }

    navigateToLoginPage= () => {
        this.props.history.push("/login");
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

                    <Box border={2} borderRadius={5} style={{
                        padding: "2vh",
                        marginTop: "30vh",
                        width: "50vw",
                        height: "30vh",
                        background: "rgb(0,0,0, 0.6)",
                        textAlign: "center"
                    }}>
                        <Typography variant={"h1"} style={{color: "rgb(255,255,255, 0.8)", fontSize: "3rem"}}>Etkinlikleri
                            keşfet</Typography>
                        <br/>
                        <Typography variant={"subtitle1"}
                                    style={{color: "rgb(255,255,255, 0.6)", fontSize: "2rem", fontStyle: "italic"}}>Çevrendeki
                            etkinlikleri keşfet, kaydol, katıl...</Typography>
                        <br/> <br/>
                        <Button color="primary" variant={"contained"}
                                style={{opacity: "0.8"}}
                                onClick={this.navigateToEventGrid} size={"large"}>
                            Etkinlikleri gör
                        </Button>
                    </Box>
                </Grid>
                <Grid container alignItems={"center"} justify={"center"}
                      style={{bottom: "0", position: "fixed", margin: "1vh"}}>

                    <Grid item> <Button color="secondary"
                                        style={{fontStyle: "italic"}}
                                        size={"large"}
                    onClick={this.navigateToLoginPage}>
                        Yönetici Olarak Giriş Yap
                    </Button>
                    </Grid>

                </Grid>
            </div>
        )
    }
}

export default WelcomePage;
