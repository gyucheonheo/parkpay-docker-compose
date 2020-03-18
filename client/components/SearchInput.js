import React from 'react'
import Link from 'next/link'
export default class Search extends React.Component {
    state = {
        input:''
    }

    handleChange = event => {
        this.setState({ [event.target.name] : event.target.value });
    }

    render(){
        return(               
            <div className="card my-4">
            <h5 className="card-header">Search</h5>
            <div className="card-body">
              <div className="input-group">

                    <input type="text" className="form-control" name="input" value={this.state.input} onChange={this.handleChange} placeholder="Search for..." />
                    <span className="input-group-btn">
                    <Link href="/search/[keyword]" as={`/search/${this.state.input}`}>
                        <a role="button" className="btn btn-secondary">Go!</a>
                    </Link>
                    </span>

              </div>
            </div>
          </div>
        )      
    }

}