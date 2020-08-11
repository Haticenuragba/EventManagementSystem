import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles({
    root: {},
    media: {
        height: 240,
    },
});

export default function MediaCard({event}) {
    const classes = useStyles();

    return (
        <Card className={classes.root}>
            <CardActionArea>
                <CardMedia
                    className={classes.media}
                    image={event.image}
                    title={event.title}
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {event.title}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p" noWrap>
                        {event.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Grid container>
                    <Grid md={7}>
                    </Grid>
                    <Grid md={5}>
                        <Button size="small">
                            Daha Fazla Bilgi Edin
                        </Button>
                    </Grid>

                </Grid>
            </CardActions>
        </Card>
    );
}
