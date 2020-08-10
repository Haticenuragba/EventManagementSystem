import React from 'react';
import request from 'superagent';
import Dropzone from 'react-dropzone'
import DoneIcon from '@material-ui/icons/Done';
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";
import ClearIcon from '@material-ui/icons/Clear';

const CLOUDINARY_UPLOAD_PRESET = 'oyzzlogh';
const CLOUDINARY_UPLOAD_URL = 'https://api.cloudinary.com/v1_1/diijhkryx/upload';
const dropZoneStyle = {
    textAlign: "center",
    padding: "20px",
    border: "3px dashed #eeeeee",
    backgroundColor: "#fafafa",
    color: "#717070"
};

export default class ImageUploadTry extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isUploading: false,
            uploadedFile: null,
            uploadedFileCloudinaryUrl: ''
        };
    }

    onImageDrop(files) {
        this.setState({
            uploadedFile: files[0]
        });

        this.handleImageUpload(files[0]);
    }

    handleImage(url){
        this.props.onImageUpload(url);
    }

    handleImageUpload(file) {
        let upload = request.post(CLOUDINARY_UPLOAD_URL)
            .field('upload_preset', CLOUDINARY_UPLOAD_PRESET)
            .field('file', file);

        let newState = this.state;
        newState.isUploading = true;
        this.setState({
            newState
        });
        upload.end((err, response) => {
            if (err) {
                this.setState({
                    uploadedFileCloudinaryUrl: 'error'
                });
            } else if (response.body.secure_url !== '') {
                this.setState({
                    uploadedFileCloudinaryUrl: response.body.secure_url
                });
            }
            let newState = this.state;
            newState.isUploading = false;
            this.setState({
                newState
            });
            this.handleImage(this.state.uploadedFileCloudinaryUrl);
        });
    }

    addAnotherImage() {
        let newState = this.state;
        newState.uploadedFile = null;
        newState.uploadedFileCloudinaryUrl = '';
        newState.isUploading = false;
        this.setState({
            newState
        });
        this.handleImage(this.state.uploadedFileCloudinaryUrl);
    }

    render() {
        return (
            <form>
                <div className="FileUpload">
                    {
                        this.state.uploadedFileCloudinaryUrl === '' ?
                            <Dropzone
                                onDrop={this.onImageDrop.bind(this)}
                                accept="image/*"
                                multiple={false}
                                disabled={this.state.isUploading}
                            >
                                {({getRootProps, getInputProps}) => {
                                    return (
                                        <div
                                            {...getRootProps()}
                                            style={dropZoneStyle}
                                        >
                                            <input {...getInputProps()} />
                                            {
                                                this.state.isUploading ?
                                                    <CircularProgress/>
                                                    :
                                                    <Typography>Resim yüklemek için sürükleyip bırakın veya alana
                                                        tıklayın</Typography>
                                            }
                                        </div>
                                    )
                                }}
                            </Dropzone>
                            :
                            null
                    }
                    {
                        this.state.uploadedFileCloudinaryUrl === '' ? null :
                            this.state.uploadedFileCloudinaryUrl === 'error' ?
                                <div>
                                    <br/>
                                    <Grid container spacing={4}>
                                        <Grid item md={4}>
                                            <Typography>Yükleme Başarısız Lütfen tekrar deneyin.</Typography>
                                        </Grid>
                                        <Grid item>
                                            <ClearIcon color={"secondary"}/>
                                        </Grid>
                                        <Grid item>
                                            <Button variant={"contained"} color={"primary"}
                                                    onClick={() => this.addAnotherImage()}>Tekrar Dene</Button>
                                        </Grid>
                                    </Grid>
                                </div>
                                :
                                <div>
                                    <br/>
                                    <Grid container spacing={4}>
                                        <Grid item md={2}>
                                            <Typography>Dosya adı:</Typography>
                                        </Grid>
                                        <Grid item>
                                            <Typography>{this.state.uploadedFile.name} </Typography>

                                        </Grid>
                                        <Grid item>
                                            <DoneIcon color={"primary"}/>
                                        </Grid>
                                        <Grid item>
                                            <Button variant={"contained"} color={"primary"}
                                                    onClick={() => this.addAnotherImage()}>Başka bir resim
                                                yükle</Button>
                                        </Grid>
                                    </Grid>
                                </div>
                    }

                </div>


            </form>
        )
    }
}
