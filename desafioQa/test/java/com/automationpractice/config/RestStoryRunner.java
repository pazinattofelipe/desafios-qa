package com.automationpractice.config;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.automationpractice.steps.RestStepsClass;

public class RestStoryRunner extends JUnitStories {
	@SuppressWarnings("deprecation")
	public RestStoryRunner() {
		super();
		this.configuredEmbedder().candidateSteps().add(new RestStepsClass());
		
		EmbedderControls embedderControls = configuredEmbedder()
				.embedderControls();
		embedderControls.useStoryTimeoutInSecs(2000);
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE, Format.STATS, Format.HTML));
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
		StoryFinder finder = new StoryFinder();
		return finder.findPaths(codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/Reports.story"), Arrays.asList(""));
	}
}
