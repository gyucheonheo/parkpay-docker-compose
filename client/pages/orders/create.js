import React from 'react'
import Layout from '../../components/MyLayout'
import Sidebar from '../../components/Sidebar';
import axios from 'axios'
import Router from 'next/router';

export default class Createorder extends React.Component {
    state = {
        pid:0,
        state:'' ,
        plate:'',
        type:'',
        name:'',
        email:'',
        card:'',
        name_on_card:'',
        expiration_date_month: '',
        expirmation_date_year:'',
        zip:0,
        errors:{},
        parks_data: []
    }

    componentDidMount(){
        axios.get(`http://localhost:8080/parkpay/parks`)
        .then(res => {
            this.setState({
                parks_data : res.data 
            })
        })
    }
    handleChange = event => {
        this.setState({ [event.target.name] : event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();
        axios.post(`http://localhost:8080/parkpay/orders`,
        {            
            pid : this.state.pid,
            vehicle:{
               state: this.state.state,
               plate: this.state.plate,
               type: this.state.type
            },
            visitor:{
                name: this.state.name,
                email: this.state.email,
                payment_info:{
                    card: this.state.card,
                    name_on_card : this.state.name_on_card,
                    expiration_date: this.state.expiration_date_month+"/"+this.state.expiration_date_year,
                    zip: this.state.zip
                }
            }
        })
        .then(res => {
            // redirect...!
            Router.push('/orders');
       })
        .catch(err => {
            if(err.response.status == 400){
                this.setState({
                    errors : err.response.data.detail
                })
            }
        })
    }

    render(){
        const list_of_states = [
            "AK",
            "AL",
            "AR",
            "AZ",
            "CA",
            "CO",
            "CT",
            "DE",
            "FL",
            "GA",
            "HI",
            "IA",
            "ID",
            "IL",
            "IN",
            "KS",
            "KY",
            "LA",
            "MA",
            "MD",
            "ME",
            "MI",
            "MN",
            "MO",
            "MS",
            "MT",
            "NC",
            "ND",
            "NE",
            "NH",
            "NJ",
            "NM",
            "NV",
            "NY",
            "OH",
            "OK",
            "OR",
            "PA",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VA",
            "VT",
            "WA",
            "WI",
            "WV",
            "WY",
         ]
        return(
            <Layout>
                <div className="row">
                    <div className="col-md-7">
                    <h3>Create New Order</h3>
                    <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">Park</label>
                        <select className="form-control" name="pid" value={this.state.pid} onChange={this.handleChange} placeholder="Choose Park">
                            <option key="default" default>Select</option>
                            {this.state.parks_data.map(park => (
                                <option key={park.pid} value={park.pid}>{park.location_info.name},{park.location_info.region}</option>
                            ))}
                        </select>
                  
                        <small id="emailhelp" className="form-text text-muted">required</small>
                    </div>
                    <h4> Vehicle Information</h4>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">state</label>
                        <select className="form-control" name="state" value={this.state.state} onChange={this.handleChange} placeholder="Choolse State on the plate">
                            <option key="default" default>Select</option>
                            {list_of_states.map(abbr => 
                                (<option key={abbr} value={abbr}>{abbr}</option>))}
                        </select>
                        {this.state.errors.state &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.state}</small>}
                        <small id="emailhelp" className="form-text text-muted">required.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">plate</label>
                        <input className="form-control" type="text" name="plate" value={this.state.plate} onChange={this.handleChange} />
                        {this.state.errors.plate &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.plate}</small>}
                        <small id="emailhelp" className="form-text text-muted">required.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">type</label>
                        <select className="form-control" onChange={this.handleChange} name="type" value={this.state.type} placeholder="Choose a type of vehicle">
                            <option key="default" default>Select </option>
                            <option value="car">Car</option>
                            <option value="rv">RV</option>
                            <option value="motorcycle">Motorcycle</option>
                        </select>
                        {this.state.errors.type &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.type}</small>}
                        <small id="emailhelp" className="form-text text-muted">required.</small>
                    </div>
                    <h4> Visitor Information</h4>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">Name</label>
                        <input className="form-control" type="text" name="name" value={this.state.name} onChange={this.handleChange} />
                        <small id="emailhelp" className="form-text text-muted">optional.</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleinputemail1">E-mail</label>
                        <input className="form-control" type="text" name="email" value={this.state.email} onChange={this.handleChange} />
                        {this.state.errors.email &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.email}</small>}
                        <small id="emailhelp" className="form-text text-muted">required.</small>
                   </div>

                    <h4> Payment Information</h4>
                    <div className="form-group">
                        <label htmlFor="inputpassword4">Card Number</label>
                        <input className="form-control" type="text" name="card" value={this.state.card} onChange={this.handleChange} />
                        {this.state.errors.card &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.card}</small>}
                        <small id="emailhelp" className="form-text text-muted">required.</small>
                    </div>

                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleinputemail1">Name on Card</label>
                            <input className="form-control" type="text" name="name_on_card" value={this.state.name_on_card} onChange={this.handleChange} />
                            {this.state.errors.name_on_card &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.name_on_card}</small>}
                            <small id="emailhelp" className="form-text text-muted">required</small>
                        </div>
                            <div className="form-group col-md-3">
                                <label htmlFor="exampleInputEmail1">Exp Date-Month</label>
                                <input className="form-control" type="text" name="expiration_date_month" value={this.state.expiration_date_month} onChange={this.handleChange} />
                                {this.state.errors.expiration_date &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.expiration_date}</small>}
                                <small id="emailHelp" className="form-text text-muted">Required</small>
                                
                            </div>
                            <div className="form-group col-md-3">
                                <label htmlFor="exampleInputEmail1">Exp Date-Year</label>
                                <input className="form-control" type="text" name="expiration_date_year" value={this.state.expiration_date_year} onChange={this.handleChange} />
                                {this.state.errors.expiration_date &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.expiration_date}</small>}
                                <small id="emailHelp" className="form-text text-muted">Required</small>
                            </div>

                    </div>

                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="exampleInputEmail1">zip</label>
                            <input className="form-control" type="text" name="zip" value={this.state.zip} onChange={this.handleChange} />
                            {this.state.errors.zip &&  <small id="emailHelp" className="form-text text-danger">{this.state.errors.zip}</small>}
                            <small id="emailHelp" className="form-text text-muted">Required</small>
                        </div>
                    </div>
                    <input type="submit" className="btn btn-primary btn-lg btn-block" value="Place Order"></input>
                    </form>
                </div> 
                <Sidebar />
                </div>
          </Layout>
        )
    }

}