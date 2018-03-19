package com.george.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yu on 2017/5/31.
 */
public class Pagine<T> extends AbstractPageRequest implements Page<T>, Serializable {

    private static final long serialVersionUID = 3238802633704668934L;

    private long total = 0;

    private List<T> content = new ArrayList<T>();

    private Sort sort;

    /**
     * Creates a new {@link AbstractPageRequest}. Pages are zero indexed, thus
     * providing 0 for {@code page} will return the first page.
     *
     * @param page must not be less than zero.
     * @param size must not be less than one.
     */
    public Pagine(int page, int size) {
        super(page, size);
    }

    public Pagine(int page, int size, Sort sort) {
        super(page, size);
        this.sort = sort;
    }

    public Pagine(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    public Pagine(Pageable pageable) {
        this(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    }

    public Pagine(List<T> content, Pageable pageable, long total) {
        this(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        this.content = content;
        this.total = total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getNumber() {
        return getPageNumber();
    }

    public int getSize() {
        return getPageSize();
    }

    public int getNumberOfElements() {
        return content.size();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public boolean hasContent() {
        return !content.isEmpty();
    }

    public Sort getSort() {
        return this.sort;
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    public Pageable nextPageable() {
        return hasNext() ? next() : null;
    }

    public Pageable previousPageable() {
        if (hasPrevious()) {
            return previousOrFirst();
        }

        return null;
    }

    public Pageable next() {
        return new Pagine(getPageNumber() + 1, getPageSize(), getSort());
    }

    public Pageable previous() {
        return getPageNumber() == 0 ? this : new Pagine<T>(getPageNumber() - 1, getPageSize(),
                getSort());
    }

    public Pageable first() {
        return new Pagine(0, getPageSize(), getSort());
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    public long getTotalElements() {
        return total;
    }

    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return new Pagine<S>(getConvertedContent(converter), this, total);
    }

    public Iterator<T> iterator() {
        return content.iterator();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {

        String contentType = "UNKNOWN";
        List<T> content = getContent();

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(),
                contentType);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pagine<?>)) {
            return false;
        }

        Pagine<?> that = (Pagine<?>) obj;

        return this.total == that.total && super.equals(obj);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {

        int result = 17;

        result += 31 * (int) (total ^ total >>> 32);
        result += 31 * super.hashCode();

        return result;
    }

    public List<Sort.Order> getOrders() {
        if (sort == null) {
            return Collections.<Sort.Order>emptyList();
        }
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (Sort.Order order : sort) {
            orders.add(order);
        }
        return orders;
    }

    /**
     * Applies the given {@link Converter} to the content of the
     * {@link org.springframework.data.domain.Chunk}.
     *
     * @param converter must not be {@literal null}.
     * @return
     */
    protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter) {

        Assert.notNull(converter, "Converter must not be null!");

        List<S> result = new ArrayList<S>(content.size());

        for (T element : this) {
            result.add(converter.convert(element));
        }

        return result;
    }
}

