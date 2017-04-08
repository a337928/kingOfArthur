package com.arthur.designPatterns.eventListener;

import java.util.EventListener;

/**
 * Created by wangtao on 17/4/8.
 */
public interface MouseMoveListener extends EventListener {
	void mouseMove(MouseMoveEvent mouseMoveEvent);
}
