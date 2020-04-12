import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { createProject } from '../../actions/projectActions'

class AddProject extends Component {
    constructor() {
        super();
    
        this.state = {
            projectName: '',
            projectIdentifier: '',
            description: '',
            startDate: '',
            endDate: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const newProject = {...this.state};
        this.props.createProject(newProject, this.props.history)
    }

    handleChange(event) {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    }
    
    render() {
        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create / Edit Project form</h5>
                            <hr />
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        className="form-control form-control-lg " 
                                        placeholder="Project Name" 
                                        name="projectName"
                                        value={this.state.projectName}
                                        onChange={this.handleChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        className="form-control form-control-lg" 
                                        placeholder="Unique Project ID"
                                        name='projectIdentifier'
                                        value={this.state.projectIdentifier}
                                        onChange={this.handleChange}
                                    />
                                </div>

                                <div className="form-group">
                                    <textarea 
                                        className="form-control form-control-lg" 
                                        placeholder="Project Description"
                                        name='description'
                                        value={this.state.description}
                                        onChange={this.handleChange}
                                    >
                                    </textarea>
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        className="form-control form-control-lg" 
                                        name="startDate" 
                                        value={this.state.startDate}
                                        onChange={this.handleChange}
                                    />
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        className="form-control form-control-lg" 
                                        name="endDate"
                                        value={this.state.endDate}
                                        onChange={this.handleChange}
                                    />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

AddProject.propTypes = {
    CreateProject: PropTypes.func.isRequired
}

export default connect(
    null,
    { createProject}
) (AddProject)
