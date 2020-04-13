import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { createProject } from '../../actions/projectActions'
import classnames from 'classnames';

class AddProject extends Component {
    constructor() {
        super();

        this.state = {
            projectName: '',
            projectIdentifier: '',
            description: '',
            startDate: '',
            endDate: '',
            errors: {}
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors })
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        const newProject = { ...this.state };
        this.props.createProject(newProject, this.props.history)
    }

    handleChange(event) {
        const { name, value } = event.target;

        this.setState({
            [name]: value
        });
    }

    render() {
        const { errors } = this.state


        return (
            <div>
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
                                            className={classnames("form-control form-control-lg ", {
                                                "is-invalid":errors.projectName
                                            })}
                                            placeholder="Project Name"
                                            name="projectName"
                                            value={this.state.projectName}
                                            onChange={this.handleChange}
                                        />
                                        {errors.projectName && (
                                            <div className='invalid-feedback'>{errors.projectName}</div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg ", {
                                                "is-invalid":errors.projectIdentifier
                                            })}
                                            placeholder="Unique Project ID"
                                            name='projectIdentifier'
                                            value={this.state.projectIdentifier}
                                            onChange={this.handleChange}
                                        />
                                        {errors.projectIdentifier && (
                                            <div className='invalid-feedback'>{errors.projectIdentifier}</div>
                                        )}
                                    </div>

                                    <div className="form-group">
                                        <textarea
                                            className={classnames("form-control form-control-lg ", {
                                                "is-invalid":errors.description
                                            })}
                                            placeholder="Project Description"
                                            name='description'
                                            value={this.state.description}
                                            onChange={this.handleChange}
                                        >
                                        </textarea>
                                        {errors.description && (
                                            <div className='invalid-feedback'>{errors.description}</div>
                                        )}
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
            </div>
        )
    }
}

AddProject.propTypes = {
    CreateProject: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
})

export default connect(
    mapStateToProps,
    { createProject }
)(AddProject)
