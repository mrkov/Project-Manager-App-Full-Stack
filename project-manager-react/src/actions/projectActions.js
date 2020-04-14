import axios from 'axios'
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from './types'

export const createProject = (project, history) => async dispatch => {
    try {
        await axios.post
            ("/api/projects", project)
        history.push("/dashboard")

    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
}

export const getProjects = () => async dispatch => {


    const res = await axios.get
        ("/api/projects")
    dispatch({
        type: GET_PROJECTS,
        payload: res.data
    })


}

export const getProject = (projectId, history) => async dispatch => {

    try {
        const res = await axios.get
            (`/api/projects/${projectId}`);
        dispatch({
            type: GET_PROJECT,
            payload: res.data
        })

    } catch (error) {
        history.push('/dashboard')
    }

}


export const putProject = (project, history) => async dispatch => {

    try {
        await axios.put
            (`/api/projects/${project.projectIdentifier}`, project)
        history.push("/dashboard")

    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }

}

export const deleteProject = projectId => async dispatch => {
    if(window.confirm(
        "Are you sure? This will delete the project and all the related data."
    ))
    
    await axios.delete(`/api/projects/${projectId}`)
    dispatch({
        type: DELETE_PROJECT,
        payload: projectId

    })
}


