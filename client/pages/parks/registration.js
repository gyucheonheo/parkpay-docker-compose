import React from 'react'
import Layout from '../../components/MyLayout'
import Sidebar from '../../components/Sidebar';
import Alert from '../../components/Alert'
import axios from 'axios'
import Router, { useRouter } from 'next/router';

export default class Registration extends React.Component {
    state = {
        name: '',
        region: '',
        address: '',
        phone: '',
        web: '',
        lat:0.0,
        lng:0.0,
        motorcycle_in_state : 0,
        motorcycle_out_state : 0,
        car_in_state : 0,
        car_out_state :0,
        rv_in_state :0,
        rv_out_state : 0,
        errorMessage:''
    }

    handleChange = event => {
        this.setState({ [event.target.name] : event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        axios.post(`http://localhost:8080/parkpay/parks`,
        {            
            location_info:{
            name : this.state.name,
            region: this.state.region,
            address: this.state.address,
            phone: this.state.phone,
            web: this.state.web,
            geo:{
                lat: this.state.lat,
                lng: this.state.lng,
            }
        },
        payment_info:{
            motorcycle: [this.state.motorcycle_in_state, this.state.motorcycle_out_state],
            car : [this.state.car_in_state, this.state.car_out_state],
            rv : [this.state.rv_in_state, this.state.rv_out_state]
        }})
        .then(res => {
            // Redirect...!
            Router.push('/parks');
       })
        .catch(err => {
            if(err.response.status == 400){
                this.setState({
                    errorMessage : err.response.data.title
                })
            }
        })
    }

    render(){
        return(
            <Layout>
                <div className="row">
                    <div className="col-md-7">
                    <h3>Register new park</h3>
                    <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Park Name</label>
                        <input className="form-control" type="text" name="name" value={this.state.name} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Required</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Region</label>
                        <input className="form-control" type="text" name="region" value={this.state.region} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Optional</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Address</label>
                        <input className="form-control" type="text" name="address" value={this.state.address} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Required</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Phone</label>
                        <input className="form-control" type="text" name="phone" value={this.state.phone} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Optional</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Web</label>
                        <input className="form-control" type="text" name="web" value={this.state.web} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Required.</small>
                    </div>
                    <h4> Geographical Information</h4>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                        <label htmlFor="inputEmail4">Latitude</label>
                        <input className="form-control" type="text" name="lat" value={this.state.lat} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Required.</small>
                        </div>
                        <div className="form-group col-md-6">
                        <label htmlFor="inputPassword4">Longitude</label>
                        <input className="form-control" type="text" name="lng" value={this.state.lng} onChange={this.handleChange} />
                        <small id="emailHelp" className="form-text text-muted">Required.</small>
                        </div>
                    </div>
                    <h4> Payment Information</h4>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">Motorcycle(In-state)</label>
                            <input className="form-control" type="text" name="motorcycle_in_state" value={this.state.motorcycle_in_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">Motorcycle(Out-state)</label>
                            <input className="form-control" type="text" name="motorcycle_out_state" value={this.state.motorcycle_out_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">Car(In-state)</label>
                            <input className="form-control" type="text" name="car_in_state" value={this.state.car_in_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">Car(Out-state)</label>
                            <input className="form-control" type="text" name="car_out_state" value={this.state.car_out_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                    </div>

                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">RV(In-state)</label>
                            <input className="form-control" type="text" name="rv_in_state" value={this.state.rv_in_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">RV(Out-state)</label>
                            <input className="form-control" type="text" name="rv_out_state" value={this.state.rv_out_state} onChange={this.handleChange} />
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                    </div>
                    { this.state.errorMessage &&
                        <Alert type="warning" text={this.state.errorMessage} />
                    }
                    <input type="submit" className="btn btn-primary btn-lg btn-block" value="Add Park"></input>
                    </form>
                </div> 
                <Sidebar />
                </div>
          </Layout>
        )
    }

}