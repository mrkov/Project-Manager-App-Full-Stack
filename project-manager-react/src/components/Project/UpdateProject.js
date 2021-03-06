import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { getProject, putProject } from '../../actions/projectActions'
import classnames from 'classnames';

class UpdateProject extends Component {
    constructor() {
        super()

        this.state = {
            id: '',
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

        const { id,
            projectName,
            projectIdentifier,
            description,
            startDate,
            endDate } = nextProps.project;

        this.setState({
            id,
            projectName,
            projectIdentifier,
            description,
            startDate,
            endDate
        })
    }

    componentDidMount() {
        const { projectId } = this.props.match.params;
        this.props.getProject(projectId, this.props.history)
    }

    handleChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }

    handleSubmit(event) {
        event.preventDefault()

        const updatedProject = {
            id: this.state.id,
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            startDate: this.state.startDate,
            endDate: this.state.endDate
        }

        this.props.putProject(updatedProject, this.props.history)
    }

    render() {
        const { errors } = this.state;
        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Edit Project form</h5>
                            <hr />
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className={classnames("form-control form-control-lg",
                                            { "is-invalid": errors.projectName })}
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
                                        disabled
                                        name='projectIdentifier'
                                        value={this.state.projectIdentifier}
                                        onChange={this.handleChange}

                                    />
                                </div>
                                <div className="form-group">
                                    <textarea
                                        className={classnames("form-control form-control-lg",
                                            { "is-invalid": errors.description })}
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

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    putProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    project: state.project.project,
    errors: state.errors
})

export default connect(
    mapStateToProps,
    { getProject, putProject })
    (UpdateProject);
