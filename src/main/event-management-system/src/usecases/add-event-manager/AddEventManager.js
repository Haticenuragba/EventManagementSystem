import React, {Component} from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import {createMuiTheme} from "@material-ui/core/styles";
import axios from "axios";
import {headers, showErrorDialog, showSuccessDialog} from "../../common/Utils";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import {validateEmail} from "../../common/Validation";



class AddEventManager extends Component {
    backgroundImage = require('../../images/background10.jpg');


    constructor(props) {
        super(props);
        this.state = {
            eventManager: {
                username: "",
                email: ""
            }
        }
    }

    handleInputChange = e => {
        let newState = this.state;
        const name = e.target.name;
        const value = e.target.value;
        newState.eventManager[name] = value;
        this.setState({
            newState
        });

    }

    navigateToHome() {
        this.props.history.push('/admin/events');
    }

    saveEventManager(eventManager) {
        let auth = headers;
        axios.post("/event-managers", eventManager, {headers: auth})
            .then(response => {
                if (response.status === 200) {
                    showSuccessDialog("Etkinlik sorumlusu başarıyla eklendi");
                    this.navigateToHome();
                }
            }).catch(error => {
            if (error.response.data.status === 406)
                showErrorDialog(error.response.data.message);
            else
                showErrorDialog("Bir hata oluştu.");
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
                        <Card style={{
                            padding: "5vh",
                            marginTop: "30vh",
                            width: "50vw",
                            height: "30vh",
                            textAlign: "center",
                        }}>
                            <CardContent>


                                <div>
                                    <TextField name={"username"} onChange={this.handleInputChange} type={"text"}
                                               fullWidth label={"Etkinlik Sorumlusunun Adı"}/>
                                    <br/> <br/>
                                    <TextField
                                        helperText={validateEmail(this.state.eventManager.email) ? "": "Email adresi geçersiz"} name={"email"} onChange={this.handleInputChange} type={"email"} fullWidth label={"Etkinlik Sorumlusunun Email Adresi"}/>
                                    <br/><br/> <br/>
                                </div>
                                <Button color="primary" variant={"contained"}
                                        onClick={() => this.saveEventManager(this.state.eventManager)} size={"large"}>
                                    ETKİNLİK SORUMLUSUNU EKLE
                                </Button>
                            </CardContent>
                        </Card>
                </Grid>
            </div>
        )
    }
}

export default AddEventManager;
