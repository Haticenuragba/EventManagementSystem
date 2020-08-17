import React, {Component} from "react";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import SendIcon from "@material-ui/icons/Send";
import Typography from "@material-ui/core/Typography";


class AskQuestionPage extends Component {
    backgroundImage = require('../../images/background10.jpg');


    constructor(props) {
        super(props);
        this.state = {
            eventTitle: "",
            question: {
                nickname: "",
                question: ""
            }
        }
    }

    componentDidMount() {
        console.log(this.props.location.pathname);
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
                        padding: "3vh",
                        marginTop: "30vh",
                        width: "50vw",
                        height: "36vh",
                        textAlign: "center",
                    }}>
                        <CardContent>


                            <div>
                                <Typography variant={"h4"}>
                                    {this.state.eventTitle} Etkinliği
                                </Typography>
                                <br/>
                                <TextField name={"nickname"} onChange={this.handleInputChange} type={"text"}
                                           fullWidth label={"Rumuz"}/>
                                <br/> <br/>
                                <TextField name={"question"} onChange={this.handleInputChange} type={"text"}
                                           fullWidth label={"Sorunuz"}/>
                                <br/><br/><br/>
                            </div>
                            <Button color="primary" variant={"contained"} endIcon={<SendIcon/>}
                                    onClick={() => this.saveEventManager(this.state.eventManager)} size={"large"}>
                                SORUYU GÖNDER
                            </Button>
                        </CardContent>
                    </Card>
                </Grid>
            </div>
        )
    }
}

export default AskQuestionPage;
