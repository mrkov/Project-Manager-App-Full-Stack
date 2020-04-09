import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem'

export default class Dashboard extends Component {
    render() {
        return (
            <div>
                <h2>Hej haj, baš nas briga!</h2>
                <ProjectItem />
                <ProjectItem />
                <ProjectItem />
                <ProjectItem />
            </div>
        )
    }
}

