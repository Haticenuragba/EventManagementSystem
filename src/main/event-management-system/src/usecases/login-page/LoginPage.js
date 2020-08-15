import React, {Component} from "react";
import Box from "@material-ui/core/Box";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import CssBaseline from "@material-ui/core/CssBaseline";
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import {dark} from "@material-ui/core/styles/createPalette";
import axios from "axios";
import {ADMIN, EVENT_MANAGER, ROLE, showErrorDialog, TOKEN} from "../../common/Utils";

const themeDark = createMuiTheme({
    palette: {
        type: "dark"
    },
});


class LoginPage extends Component {
    backgroundImage = require('../../images/background10.jpg');


    constructor(props) {
        super(props);
        this.state = {
            loginUser: {
                username: "",
                password: ""
            }
        }
    }

    handleInputChange = e => {
        let newState = this.state;
        const name = e.target.name;
        const value = e.target.value;
        newState.loginUser[name] = value;
        this.setState({
            newState
        });

    }

    navigateToAdmin(){
        this.props.history.push('/admin/events');
    }

    navigateToManager(){
        this.props.history.push('/event-manager/events');
    }


    login(loginUser){
        axios.post("/login", loginUser)
            .then(response => {
                if(response.status === 200) {
                    localStorage.setItem(TOKEN, "Bearer " + response.data.token);
                    localStorage.setItem(ROLE, response.data.role.sort()[0]);
                    if(localStorage.getItem(ROLE) === ADMIN){
                        this.navigateToAdmin();
                    }
                    else if(localStorage.getItem(ROLE) === EVENT_MANAGER){
                        this.navigateToManager();
                    }
                }
            }).catch(error => {
            if (error.response.data.status === 406)
                showErrorDialog(error.response.data.message);
            else
                showErrorDialog("Bir hata oluştu, lütfen bilgileri kontrol edin.");
        });
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
                                <TextField name={"username"} onChange={this.handleInputChange} type={"text"} fullWidth label={"Kullanıcı Adı"}/>
                                <br/> <br/>
                                <TextField name={"password"} onChange={this.handleInputChange} type={"password"} fullWidth label={"Şifre"}/>
                                <br/><br/> <br/>
                            </div>
                            <Button color="primary" variant={"contained"}
                                    onClick={() => this.login(this.state.loginUser)} size={"large"}>
                                GİRİŞ YAP
                            </Button>
                        </ThemeProvider>
                    </Box>
                </Grid>
            </div>
        )
    }
}

export default LoginPage;
