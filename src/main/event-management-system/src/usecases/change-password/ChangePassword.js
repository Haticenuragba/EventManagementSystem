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



class ChangePassword extends Component {
    backgroundImage = require('../../images/background10.jpg');


    constructor(props) {
        super(props);
        this.state = {
                oldPassword: "",
                newPassword: ""
        }
    }

    handleInputChange = e => {
        let newState = this.state;
        const name = e.target.name;
        const value = e.target.value;
        newState[name] = value;
        this.setState({
            newState
        });

    }

    navigateToHome() {
        this.props.history.push('/manager/events');
    }

    changePassword() {

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
                                <TextField name={"oldPassword"} onChange={this.handleInputChange} type={"password"}
                                           fullWidth label={"Eski şifreniz"}/>
                                <br/> <br/>
                                <TextField name={"newPassword"} onChange={this.handleInputChange} type={"password"} fullWidth label={"Yeni şifreniz"}/>
                                <br/><br/> <br/>
                            </div>
                            <Button color="primary" variant={"contained"}
                                    onClick={() => this.changePassword()} size={"large"}>
                                ŞİFREYİ DEĞİŞTİR
                            </Button>
                        </CardContent>
                    </Card>
                </Grid>
            </div>
        )
    }
}

export default ChangePassword;
