package com.example.blog;

import java.time.OffsetDateTime;
import java.util.Optional;

import am.ik.blog.entry.*;
import am.ik.blog.entry.factory.EntryFactory;

import org.springframework.core.io.Resource;

public class EntryCreator {
	public static Entry createEntry(EntryId entryId, Content content, Title title,
			Categories categories, Tags tags, Author created, Author updated) {
		// TODO: 実装してください
		return Entry.builder()
				.entryId(entryId)
				.content(content)
				.created(created)
				.frontMatter(new FrontMatter(title, categories, tags))
				.updated(updated)
				.build()
				.useFrontMatterDate();
	}

	public static Optional<Entry> createEntryFromMarkdown(Resource resource,
			Author created, Author updated) {
		EntryFactory factory = new EntryFactory();
		return factory.createFromYamlFile(resource)
				.map(builder -> builder
						.entryId(EntryId.fromFileName(resource.getFilename()))
						.created(created)
						.updated(updated)
						.build());
	}
}
