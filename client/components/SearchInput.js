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
            <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
              <div class="input-group">

                    <input type="text" class="form-control" name="input" value={this.state.input} onChange={this.handleChange} placeholder="Search for..." />
                    <span class="input-group-btn">
                    <Link href="/search/[keyword]" as={`/search/${this.state.input}`}>
                        <a role="button" class="btn btn-secondary">Go!</a>
                    </Link>
                    </span>

              </div>
            </div>
          </div>
        )      
    }

}