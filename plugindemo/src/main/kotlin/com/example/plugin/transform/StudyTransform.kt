package com.example.plugin.transform

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.google.common.collect.ImmutableSet
import org.gradle.api.logging.Logger

class StudyTransform constructor(val logger: Logger): Transform() {
    override fun getName(): String {
        return TRANSFROM_NAME
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return ImmutableSet.of(QualifiedContent.DefaultContentType.CLASSES,
            QualifiedContent.DefaultContentType.RESOURCES)
    }

    override fun isIncremental(): Boolean {
        return true
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return ImmutableSet.of()
    }

    override fun getReferencedScopes(): MutableSet<in QualifiedContent.Scope> {
        return ImmutableSet.of(QualifiedContent.Scope.SUB_PROJECTS,
            QualifiedContent.Scope.PROJECT,
            QualifiedContent.Scope.EXTERNAL_LIBRARIES)
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        logger.lifecycle("start StudyTransform now ...")
        super.transform(transformInvocation)
        transformInvocation?.apply {
            logger.lifecycle("context: $context")

            logger.lifecycle("outputProvider: $outputProvider")

            logger.lifecycle("print inputs: ")
            printInputs(inputs)

            logger.lifecycle("print refrenced inputs: ")
            printInputs(referencedInputs)

            logger.lifecycle("secondaryInputs: ")
            printInputs(referencedInputs)
        }
    }

    private fun printInputs(inputs: Collection<TransformInput>) {
        inputs.forEach {
            logger.lifecycle("jar inputs: ")
            it.jarInputs?.forEachIndexed { idx, jarInput ->
                logger.lifecycle("idx: $idx, name: ${jarInput.name}, toString: $jarInput" )
            }

            logger.lifecycle("directory inputs: ")
            it.directoryInputs?.forEachIndexed { index, dirInput ->
                logger.lifecycle("idx: $index, toString: $dirInput")
            }
        }
    }

    companion object {
        const val TRANSFROM_NAME = "StudyTransform"
    }
}