import React, {Component} from "react";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import CssBaseline from "@material-ui/core/CssBaseline";
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import {dark} from "@material-ui/core/styles/createPalette";

const themeDark = createMuiTheme({
    palette: {
        type: "dark"
    },
});


class LoginPage extends Component {
    backgroundImage = require('../../images/background10.jpg');


    constructor(props) {
        super(props);
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

                    <Box borderColor={"#000"} border={2} borderRadius={5} style={{
                        padding: "5vh",
                        marginTop: "30vh",
                        width: "50vw",
                        height: "30vh",
                        background: "rgb(0,0,0, 0.6)",
                        textAlign: "center",
                    }}>
                        <ThemeProvider theme={themeDark}>
                            <CssBaseline/>
                            <div>
                                <TextField type={"text"} fullWidth label={"Kullanıcı Adı"}/>
                                <br/> <br/>
                                <TextField type={"password"} fullWidth label={"Şifre"}/>
                                <br/><br/> <br/>
                                <Button color="primary" variant={"contained"}
                                        style={{opacity: "0.8"}}
                                        onClick={this.navigateToEventGrid} size={"large"}>
                                    Giriş Yap
                                </Button>
                            </div>
                        </ThemeProvider>
                    </Box>
                </Grid>
            </div>
        )
    }
}

export default LoginPage;
