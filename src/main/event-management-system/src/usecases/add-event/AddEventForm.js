import React, {Component} from 'react';
import TextField from '@material-ui/core/TextField';
import * as utils from '../../common/Utils'
import Button from "@material-ui/core/Button";
import MapView from "../../common/MapView";
import axios from "axios";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import MenuItem from "@material-ui/core/MenuItem";
import ImageUploader from "../../common/ImageUploader";
import SendIcon from '@material-ui/icons/Send';
import ClearIcon from '@material-ui/icons/Clear';
import Box from "@material-ui/core/Box";

import 'sweetalert2/src/sweetalert2.scss'
import {headers, showErrorDialog, showSuccessDialog} from "../../common/Utils";
import Select from "@material-ui/core/Select";
import {validateLength} from "../../common/Validation";


class AddEventForm extends Component {

    customAttribute = {question: "", type: 0}
    isUpdate = false;
    eventTitleToUpdate = '';
    backgroundImage = require('../../images/background10.jpg');

    constructor(props) {
        super(props);
        this.isUpdate = props.location.state.isUpdate;
        this.eventTitleToUpdate = props.location.state.eventTitleToUpdate;
        this.state = {
            prevCount: 0,
            managers: [""],
            event: {
                title: "",
                description: "",
                quota: 0,
                startDate: utils.getDateOfToday(),
                endDate: utils.getDateOfToday(),
                latitude: 37.022195,
                longitude: 35.293555,
                image: utils.defaultImageUrl,
                customAttributes: [],
                managerName: ""
            }

        }

    }


    componentDidMount() {
      this.fetchEventManagers();
        if (this.isUpdate) {
            axios.get("/events/" + this.eventTitleToUpdate)
                .then(response => {
                    if (response.status === 200) {
                        let newState = this.state;
                        newState.event = response.data;
                        this.setState({
                            newState
                        });
                    }
                })
                .catch(error => {
                    console.log(error.response);
                })
        }
    }

    fetchEventManagers(){
        let auth = headers;
        axios.get("/event-managers", {headers: auth})
            .then(response => {
                if (response.status === 200) {
                    let newState = this.state;
                    newState.managers = response.data;
                    this.setState({newState});
                }
            }).catch(error => {
            showErrorDialog("Etkinlik görevli listesi yüklenemedi");
        });
    }

    saveNewEvent(e) {
        let auth = headers;
        axios.post("/events", e, {headers: auth})
            .then(response => {
                console.log(response);
                showSuccessDialog("Etkinlik başarıyla kaydedildi");
                this.props.history.push('/admin/events');
            })
            .catch(error => {
                if (error.response.data.status === 406)
                    showErrorDialog(error.response.data.message);
                else
                    showErrorDialog("Bir hata oluştu, lütfen bilgileri kontrol edin.");
            })
    }

    updateExistingEvent(e) {
        let auth = headers;
        axios.put("/events/" + this.eventTitleToUpdate, e, {headers: auth})
            .then(response => {
                console.log(response);
                showSuccessDialog("Etkinlik başarıyla güncellendi");
                this.props.history.push('/admin/events/' + e.title);
            })
            .catch(error => {
                if (error.response.data.status === 406)
                    showErrorDialog(error.response.data.message);
                else
                    showErrorDialog("Bir hata oluştu, lütfen bilgileri kontrol edin.");
            })
    }

    handleLocation = (lat, lng) => {
        let newState = this.state;
        newState.event["longitude"] = lng;
        newState.event["latitude"] = lat;
        this.setState({
            newState
        });
    }

    handleImage = (url) => {
        let newState = this.state;
        if (url === 'error' || url === '' || !url) {
            newState.event.image = utils.defaultImageUrl;
        } else {
            newState.event.image = url;
            this.backgroundImage = url;
        }
        this.setState({
            newState
        });

    }

    handleInputChange = e => {
        let newState = this.state;
        const name = e.target.name;
        const value = e.target.value;
        newState.event[name] = value;
        this.setState({
            newState
        });

    }

    handleQuestionCountSelection = e => {
        let count = e.target.value;
        if (count !== "" && count >= 0) {
            let newState = this.state;

            let prev = this.state.prevCount;
            for (let i = 0; i < prev - count; i++) {
                newState.event.customAttributes.pop();
            }
            for (let i = prev; i < count; i++) {
                newState.event.customAttributes[i] = {...this.customAttribute};
            }
            newState.prevCount = count;
            this.setState({
                newState
            });
        }
    }

    handleQuestionChange(e, index) {
        let newState = this.state;
        const value = e.target.value;
        newState.event.customAttributes[index]['question'] = value;
        console.log(newState.event.customAttributes[index]);
        this.setState({
            newState
        });
        console.log(this.state.event.customAttributes);
    }

    handleTypeChange(e, index) {
        let newState = this.state;
        const value = e.target.value;
        newState.event.customAttributes[index]['type'] = value;
        this.setState({
            newState
        });
        console.log(this.state);
    }

    render() {
        return (
            <div style={{
                backgroundImage: "url(" + this.backgroundImage + ")",
                backgroundRepeat: "no-repeat", backgroundAttachment: "fixed",
                backgroundPosition: "center", backgroundSize: "cover",
                maxWidth: "100%", marginTop: "0.7vh"
            }}>
                <Box>
                    <Grid container spacing={2} alignItems={"center"} justify={"center"}
                          style={{maxWidth: "100%"}}
                    >
                        <Grid item md={7}>
                            <Card>
                                <CardContent>
                                    <Box p={5}>
                                        <Typography variant={"h5"}>
                                            Bir Etkinlik Ekleyin
                                        </Typography>
                                        <br/>
                                        <div>

                                            <TextField required name="title" label="Etkinlik Adı" type="text" fullWidth
                                                       value={this.state.event.title}
                                                       onChange={this.handleInputChange}
                                                       helperText={validateLength(this.state.event.title, 3, 64) ?
                                                       "" : "Etkinlik ismi en az 3, en fazla 64 karakter olmalıdır."}/>


                                        </div>
                                        <br/>
                                        <div>

                                            <TextField fullWidth required name="startDate"
                                                       label="Etkinlik Başlangıç Tarihi"
                                                       type="date" value={this.state.event.startDate}
                                                       onChange={this.handleInputChange}/>
                                        </div>
                                        <br/>
                                        <div>

                                            <TextField fullWidth required name="endDate" label="Etkinlik Bitiş Tarihi"
                                                       type="date"
                                                       value={this.state.event.endDate}
                                                       onChange={this.handleInputChange}/>
                                        </div>
                                        <br/>
                                        <div>
                                            <TextField
                                                required
                                                name="description"
                                                label="Etkinlik Açıklaması"
                                                multiline
                                                margin="normal"
                                                fullWidth
                                                onChange={this.handleInputChange}
                                                value={this.state.event.description}
                                                helperText={validateLength(this.state.event.description, 16, 255) ?
                                                    "" : "Etkinlik açıklaması en az 16 karakter olmalıdır"}
                                            />
                                        </div>
                                        <br/>
                                        <div>

                                            <TextField fullWidth required name="quota" label="Maksimum Katılımcı Sayısı"
                                                       type="number" value={this.state.event.quota}
                                                       onChange={this.handleInputChange}
                                            helperText={this.state.event.quota <=0 ? "Etkinlik kontenjanı en az 1 olmalıdır." : ""}/>
                                        </div>
                                        <br/>
                                        <div>
                                            <Typography color={"textSecondary"}>Lütfen Etkinlik için bir resim
                                                ekleyin</Typography>
                                            <ImageUploader onImageUpload={this.handleImage}/>
                                        </div>
                                        <br/>
                                        <br/>
                                        <div>
                                            <Typography color="textSecondary" gutterBottom>
                                                Lütfen etkinliğin gerçekleşeceği yeri haritadan seçin.*
                                            </Typography>
                                        </div>
                                        <div>
                                            <MapView onLocationChange={this.handleLocation}
                                                     data={{
                                                         lat: this.state.event.latitude,
                                                         lng: this.state.event.longitude,
                                                         isConstant: false
                                                     }}/>
                                        </div>
                                        <br/><br/>
                                        <div>
                                            <Select
                                                disabled={this.isUpdate}
                                                name="managerName"
                                                label={"Etkinlik sorumlusu seçin"}
                                                fullWidth
                                                defaultValue={0}
                                                required
                                                onChange={this.handleInputChange}
                                            >
                                                <MenuItem value={0} disabled>Etkinlik sorumlusunu seçiniz</MenuItem>
                                                {
                                                    this.state.managers.map((manager, index) =>
                                                    {
                                                       return( <MenuItem key={index} value={manager}>{manager}</MenuItem>);
                                                    })
                                                }
                                            </Select>
                                        </div>
                                        <br/>
                                        <div>
                                            <TextField
                                                type="number"
                                                fullWidth
                                                label="Eklemek istediğiniz soru sayısını seçin."
                                                onChange={this.handleQuestionCountSelection}/>
                                        </div>
                                        <br/>
                                        {this.state.event.customAttributes.map((customAttribute, index) => (
                                            <div key={index}>
                                                <Grid container spacing={4}>
                                                    <Grid item md={6}>
                                                        <TextField
                                                            type="text"
                                                            fullWidth
                                                            name="question"
                                                            label="Sorunuz"
                                                            onChange={(e) => this.handleQuestionChange(e, index)}
                                                            value={this.state.event.customAttributes[index].question}
                                                        />
                                                    </Grid>
                                                    <Grid item md={6}>
                                                        <TextField
                                                            select
                                                            name="type"
                                                            label="Cevap Türü"
                                                            fullWidth
                                                            onChange={(e) => this.handleTypeChange(e, index)}
                                                            value={this.state.event.customAttributes[index].type}
                                                        >
                                                            <MenuItem value="text">Yazı</MenuItem>
                                                            <MenuItem value="number">Sayı</MenuItem>
                                                            <MenuItem value="date">Tarih</MenuItem>
                                                        </TextField>
                                                    </Grid>
                                                </Grid>
                                            </div>
                                        ))}
                                        <br/>
                                    </Box>
                                </CardContent>
                                <CardActions>
                                    <Grid container alignItems={"flex-start"} justify={"flex-end"} direction={"row"}>
                                        <Grid>
                                            <Button color="secondary" variant={"contained"} endIcon={<ClearIcon/>}
                                                    size={"large"}>
                                                Formu Temizle
                                            </Button>
                                        </Grid>
                                        <Grid>
                                            <Button color="primary" variant={"contained"}
                                                    onClick={() => this.isUpdate ? this.updateExistingEvent(this.state.event) : this.saveNewEvent(this.state.event)}
                                                    endIcon={<SendIcon/>} size={"large"}>
                                                Gönder
                                            </Button>
                                        </Grid>
                                    </Grid>
                                </CardActions>
                            </Card>
                        </Grid>


                    </Grid>
                </Box>
            </div>
        );
    }
}

export default AddEventForm;
