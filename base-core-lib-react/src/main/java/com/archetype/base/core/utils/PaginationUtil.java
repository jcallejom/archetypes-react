/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.utils;



import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import com.archetype.base.core.model.dto.BaseVersion;
import com.archetype.base.core.model.dto.BaseVo;
import com.archetype.base.core.model.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the
 * <a href="https://developer.github.com/v3/#pagination">GitHub API</a>, and
 * follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link
 * header)</a>.
 */
public final class PaginationUtil {

	/** The Constant HEADER_X_TOTAL_COUNT. */
	private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
	
	/** The Constant HEADER_LINK_FORMAT. */
	private static final String HEADER_LINK_FORMAT = "<{0}>; rel=\"{1}\"";

	/**
	 * Instantiates a new pagination util.
	 */
	private PaginationUtil() {
	}

	/**
	 * Generate pagination headers for a Spring Data
	 * {@link org.springframework.data.domain.Page} object.
	 *
	 * @param <T>        The type of object.
	 * @param uriBuilder The URI builder.
	 * @param page       The page.
	 * @return http header.
	 */
	public static <T> HttpHeaders generatePaginationHttpHeaders(final UriComponentsBuilder uriBuilder,
			final Page<T> page) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
		int pageNumber = page.getNumber();
		int pageSize = page.getSize();
		StringBuilder link = new StringBuilder();
		if (pageNumber < page.getTotalPages() - 1) {
			link.append(prepareLink(uriBuilder, pageNumber + 1, pageSize, "next")).append(",");
		}
		if (pageNumber > 0) {
			link.append(prepareLink(uriBuilder, pageNumber - 1, pageSize, "prev")).append(",");
		}
		link.append(prepareLink(uriBuilder, page.getTotalPages() - 1, pageSize, "last")).append(",")
				.append(prepareLink(uriBuilder, 0, pageSize, "first"));
		headers.add(HttpHeaders.LINK, link.toString());
		return headers;
	}

	/**
	 * All fields for.
	 *
	 * @param c the c
	 * @return the stream
	 */
	@SuppressWarnings("rawtypes")
	public static Stream<Field> allFieldsFor(final Class c) {
		return walkInheritanceTreeFor(c).flatMap(k -> Arrays.stream(k.getDeclaredFields()));
	}

	/**
	 * Walk inheritance tree for.
	 *
	 * @param c the c
	 * @return the stream
	 */
	@SuppressWarnings("rawtypes")
	private static Stream<Class> walkInheritanceTreeFor(final Class c) {
		return iterate(c, k -> Optional.ofNullable(k.getSuperclass()));
	}

	/**
	 * Iterate.
	 *
	 * @param <T> the generic type
	 * @param seed the seed
	 * @param fetchNextFunction the fetch next function
	 * @return the stream
	 */
	private static <T> Stream<T> iterate(final T seed, final Function<T, Optional<T>> fetchNextFunction) {
		Objects.requireNonNull(fetchNextFunction);

		Iterator<T> iterator = new Iterator<T>() {
			private Optional<T> t = Optional.ofNullable(seed);

			public boolean hasNext() {
				return t.isPresent();
			}

			public T next() {
				if (t.isPresent()) {
					T v = t.get();
					t = fetchNextFunction.apply(v);
					return v;
				} else {
					throw new NoSuchElementException();
				}
			}
		};

		return StreamSupport.stream(
		Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED | Spliterator.IMMUTABLE), false);
	}
	
	/**
	 * Parses the pageable sort.
	 *
	 * @param <T> the generic type
	 * @param c the c
	 * @param pageable the pageable
	 * @return the pageable
	 */
	public static <T extends BaseVo> Pageable parsePageableSort(final Class<T> c, final Pageable pageable) {
		List<Field> fields = allFieldsFor(c).collect(Collectors.toList());
		List<Sort.Order> mappedSorts = new ArrayList<>();
		for (Iterator<Sort.Order> iterator = pageable.getSort().iterator(); iterator.hasNext();) {
			Sort.Order sort = iterator.next();
			boolean mapped = false;
			for (Field fieldAux : fields) {
				JsonProperty annotation = fieldAux.getAnnotation(JsonProperty.class);
				if (annotation != null && annotation.value().equals(sort.getProperty())) {
					Sort.Order newSort = new Sort.Order(sort.getDirection(), fieldAux.getName());
					mappedSorts.add(newSort);
					mapped = true;
					break;
				}

			}
			if (!mapped) {
				mappedSorts.add(sort);
			}
		}

		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(mappedSorts));
	}
	
	/**
	 * Parses the pageable sort entity.
	 *
	 * @param <T> the generic type
	 * @param c the c
	 * @param pageable the pageable
	 * @return the pageable
	 */
	public static <T extends BaseVersion> Pageable parsePageableSortEntity(final Class<T> c, final Pageable pageable) {
		List<Field> fields = allFieldsFor(c).collect(Collectors.toList());
		List<Sort.Order> mappedSorts = new ArrayList<>();
		for (Iterator<Sort.Order> iterator = pageable.getSort().iterator(); iterator.hasNext();) {
			Sort.Order sort = iterator.next();
			boolean mapped = false;
			for (Field fieldAux : fields) {
				JsonProperty annotation = fieldAux.getAnnotation(JsonProperty.class);
				if (annotation != null && annotation.value().equals(sort.getProperty())) {
					Sort.Order newSort = new Sort.Order(sort.getDirection(), fieldAux.getName());
					mappedSorts.add(newSort);
					mapped = true;
					break;
				}

			}
			if (!mapped) {
				mappedSorts.add(sort);
			}
		}

		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(mappedSorts));
	}
	
	/**
	 * Parses the pageable sort reques.
	 *
	 * @param <T> the generic type
	 * @param c the c
	 * @param pageable the pageable
	 * @return the pageable
	 */
	//Class BaseRequest<T extends BaseRequest<?>>
	public static <T extends BaseRequest> Pageable parsePageableSortReques(final Class<T> c,
			final Pageable pageable) {
		List<Field> fields = allFieldsFor(c).collect(Collectors.toList());
		List<Sort.Order> mappedSorts = new ArrayList<>();
		for (Iterator<Sort.Order> iterator = pageable.getSort().iterator(); iterator.hasNext();) {
			Sort.Order sort = iterator.next();
			boolean mapped = false;
			for (Field fieldAux : fields) {
				JsonProperty annotation = fieldAux.getAnnotation(JsonProperty.class);
				if (annotation != null && annotation.value().equals(sort.getProperty())) {
					Sort.Order newSort = new Sort.Order(sort.getDirection(), fieldAux.getName());
					mappedSorts.add(newSort);
					mapped = true;
					break;
				}

			}
			if (!mapped) {
				mappedSorts.add(sort);
			}
		}

		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(mappedSorts));
	}

	/**
	 * Prepare link.
	 *
	 * @param uriBuilder the uri builder
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @param relType the rel type
	 * @return the string
	 */
	private static String prepareLink(final UriComponentsBuilder uriBuilder,
			final int pageNumber, final int pageSize,
			final String relType) {
		return MessageFormat.format(HEADER_LINK_FORMAT,
				preparePageUri(uriBuilder, pageNumber, pageSize), relType);
	}

	/**
	 * Prepare page uri.
	 *
	 * @param uriBuilder the uri builder
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return the string
	 */
	private static String preparePageUri(final UriComponentsBuilder uriBuilder, final int pageNumber,
			final int pageSize) {
		return uriBuilder.replaceQueryParam("page", Integer.toString(pageNumber))
				.replaceQueryParam("size", Integer.toString(pageSize)).toUriString().replace(",", "%2C")
				.replace(";", "%3B");
	}
}
