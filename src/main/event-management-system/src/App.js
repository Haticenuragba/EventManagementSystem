import React, {Component} from 'react';
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import AddEventForm from "./usecases/admin/add-event/AddEventForm";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }



    render() {
        return (
            <div>
            <AddEventForm data={{isUpdate:false, eventTitleToUpdate: 'Deneme'}}/>
          {/* <EventsGrid/>*/}
            </div>
        );
    }
}

export default App;
