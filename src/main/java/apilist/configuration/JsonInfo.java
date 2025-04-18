package apilist.configuration;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JsonInfo<T> {

	private InputStream stream;
	private TypeReference<List<T>> type;

}
