package com.example.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class TransfromTask : DefaultTask() {

    @TaskAction
    fun doTransform() {
        logger.lifecycle( "now begin the transformTask now ....")

    }
}